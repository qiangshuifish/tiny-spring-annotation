package xin.qiangshuidiyu.spring.beans.annotation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import xin.qiangshuidiyu.spring.BeanReference;
import xin.qiangshuidiyu.spring.beans.AbstractBeanDefinitionReader;
import xin.qiangshuidiyu.spring.beans.BeanDefinition;
import xin.qiangshuidiyu.spring.beans.PropertyValue;
import xin.qiangshuidiyu.spring.beans.io.ResourceLoader;
import xin.qiangshuidiyu.spring.beans.io.UrlResource;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 注解版 BeanDefinition 加载器
 *
 * @author wpy
 * @date 2018/6/14 10:35
 */
public class AnnotationBeanDefinitionReader extends AbstractBeanDefinitionReader {

    /**
     * 用来存储所有加了 @Component 注解的类的全类名
     */
    private final List<String> classNames;

    public AnnotationBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
        classNames = new ArrayList<>();
    }

    @Override
    public void loadDefinitionReader(String packageName) throws Exception {
        UrlResource resource = (UrlResource) getResourceLoader().getResource(packageName);
        // 这里需要的是文件系统，和XML需要文件流不太一样
        URL url = resource.getUrl();
        // 扫描包的根目录
        File baseDirectory = new File(url.getFile());
        // 所有包中所有加了 @Component 的类
        scan(packageName, baseDirectory);

        for (String className : classNames) {
            processBeanDefinition(className);
        }
    }

    /**
     * 通过完整的className 来加载类为BeanDefinition
     *
     * @param className
     * @throws Exception
     */
    private void processBeanDefinition(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Component component = clazz.getAnnotation(Component.class);
        if (Objects.isNull(component)) {
            return;
        }
        // 使用一个beanDefinition来描述这个 bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClassName(className);
        beanDefinition.setBeanClass(clazz);
//        beanDefinition.setBean(clazz.newInstance()); //创建bean应该交给工厂

        // 如果使用了别名就使用别名
        String componentName = component.value().equals("") ? clazz.getSimpleName() : component.value();
        //注册 bean
        registerBean(WordUtils.uncapitalize(componentName), beanDefinition);
        // 加载类的属性到 beanDefinition
        processProperty(beanDefinition);
    }

    /**
     * 给 beanDefinition添加属性列表
     * @param beanDefinition
     * @throws Exception
     */
    private void processProperty(BeanDefinition beanDefinition) throws Exception {
        // 获取所有属性，包括私有的
        Field[] fields = beanDefinition.getBeanClass().getDeclaredFields();
        for (Field field : fields) {
            Resource resource = field.getAnnotation(Resource.class);
            if (Objects.nonNull(resource)) {

                // 如果组件指定了名称则通过这个名称加载，否则通过属性名加载(这里暂时只考虑注入时属性名为class名字)
                String fieldComponentName = resource.name().equals("") ? field.getName() : resource.name();

                BeanReference beanReference = new BeanReference(fieldComponentName);
                beanDefinition.getPropertyValues()
                        .addPropertyValue(new PropertyValue(field.getName(),beanReference));
            }

            Value value = field.getAnnotation(Value.class);
            if(Objects.nonNull(value)){
                if(StringUtils.isNoneBlank(value.value()) && value.value().matches("\\$\\{(.+?)}")){
                    String valueName = value.value()
                            .replace("$", "")
                            .replace("{", "")
                            .replace("}", "");
                    if(field.getType().equals(String.class)){
                        beanDefinition.getPropertyValues()
                                .addPropertyValue(new PropertyValue(field.getName(),getProperties().getProperty(valueName)));
                    }
                    // 处理其他基础属性
                }
            }
        }
    }



    /**
     * 注册 beanDefinition
     * @param componentName
     * @param beanDefinition
     */
    private void registerBean(String componentName, BeanDefinition beanDefinition) {
        this.getRegistry().put(componentName, beanDefinition);
    }

    /**
     * 扫描包，只扫描被 Component注解修饰的类
     * @param packageName
     * @param directory
     * @throws ClassNotFoundException
     */
    private void scan(String packageName, File directory) throws ClassNotFoundException {
        assert directory != null : "包 " + packageName + " 不存在";
        File[] files = directory.listFiles();
        assert files != null : directory.getPath() + " :不是目录";
        for (File file : files) {
            if (file.isDirectory()) {
                scan(packageName + "." + file.getName(), file);
            } else {
                String className = packageName + "." + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(className);
                Component component = clazz.getAnnotation(Component.class);
                if (Objects.nonNull(component)) {
                    classNames.add(className);
                }
            }
        }
    }

    @Override
    public void loadPropertiesReader(String fileName) throws IOException {
        InputStream inputStream = this.getResourceLoader().getResource(fileName).getInputStream();
        this.getProperties().load(inputStream);
    }
}
