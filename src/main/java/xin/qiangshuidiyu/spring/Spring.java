package xin.qiangshuidiyu.spring;

import org.junit.Test;
import xin.qiangshuidiyu.spring.BeanReference;
import xin.qiangshuidiyu.spring.app.HelloWorldService;
import xin.qiangshuidiyu.spring.app.OutputService;
import xin.qiangshuidiyu.spring.beans.BeanDefinition;
import xin.qiangshuidiyu.spring.beans.annotation.Component;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 第一版，支持Component和Resource 的注入
 * @author wpy
 * @date 2018/6/13 15:06
 */
public class Spring {

    private final List<String> classNames = new ArrayList<>();
    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Test
    public void test() throws Exception {
        String basePackage = "xin.qiangshuidiyu.spring.app";
        URL url = this.getClass().getClassLoader().getResource(replaceAll(basePackage));
        assert url != null:basePackage + " 不存在";

        File baseDirectory = new File(url.getFile());

        scan(basePackage,baseDirectory);

        for (String className : classNames) {
            processBeanDefinition(className);
        }

        System.out.println(classNames);
        System.out.println(beanDefinitionMap);

        OutputService outputService = (OutputService)beanDefinitionMap.get(OutputService.class.getName()).getBean();
        HelloWorldService helloWorldService = (HelloWorldService)beanDefinitionMap.get(HelloWorldService.class.getName()).getBean();
        outputService.output();
        helloWorldService.helloWorld();

    }

    /**
     *
     * @param className
     * @throws Exception
     */
    private void processBeanDefinition(String className) throws Exception{
        Class<?> clazz = Class.forName(className);

        Component component = clazz.getAnnotation(Component.class);
        if(Objects.isNull(component)){
            return;
        }
        String beanName = clazz.getName();

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClassName(beanName);
        beanDefinition.setBeanClass(clazz);
        beanDefinition.setBean(clazz.newInstance());

        //注册 bean
        regiesterBean(beanName, beanDefinition);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Resource resource = field.getAnnotation(Resource.class);
            if(Objects.nonNull(resource)){
                String name = field.getType().getName();
                BeanDefinition definition = this.beanDefinitionMap.get(name);
                if(Objects.isNull(definition)){
                    //如果扫描的类中没有 这个属性的类型 报错，有的话就递归创建它

                    assert classNames.contains(name) : beanName+ "."+field.getName()+"不存的类型"+ name;
                    processBeanDefinition(name);
                    definition = this.beanDefinitionMap.get(name);
                }
                field.setAccessible(true);
                field.set(beanDefinition.getBean(),definition.getBean());

            }
            // 处理其他基础属性
        }


    }

    private void regiesterBean(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName,beanDefinition);
    }

    private void processBeanDefinition(){

    }


    private void scan(String packageName,File directory){
        assert directory != null : "package "+  packageName+ " 不存在";
        File[] files = directory.listFiles();
        assert files != null : directory.getPath()+ " :不是目录";
        for (File file : files) {
            if(file.isDirectory()){
                scan(packageName+"."+file.getName(),file);
            }else {
                String className = packageName + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }
    }

    private String replaceAll(String packageName){
        return packageName.replaceAll("\\.","/");
    }

}
