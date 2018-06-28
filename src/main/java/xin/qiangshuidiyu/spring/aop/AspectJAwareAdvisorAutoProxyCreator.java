package xin.qiangshuidiyu.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import xin.qiangshuidiyu.spring.aop.aspectj.AspectJExpressionPointcut;
import xin.qiangshuidiyu.spring.beans.factory.AbstractBeanFactory;
import xin.qiangshuidiyu.spring.beans.factory.BeanFactory;

import java.util.List;

/**
 * @author wpy
 * @date 2018/6/22 19:45
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor,BeanFactoryAware{

    private AbstractBeanFactory beanFactory;

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception {
        if(bean instanceof AspectJExpressionPointcut){
            return bean;
        }
        if(bean instanceof MethodInterceptor){
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeans(AspectJExpressionPointcutAdvisor.class);

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if(advisor.getPointcut().getClassFilter().matches(bean.getClass())){
                ProxyFactory proxyFactory = new ProxyFactory();

                proxyFactory.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                proxyFactory.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

                TargetSource targetSource = new TargetSource(bean,bean.getClass(),bean.getClass().getInterfaces());
                proxyFactory.setTargetSource(targetSource);

                return proxyFactory.getProxy();
            }
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }
}
