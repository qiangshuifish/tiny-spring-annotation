package xin.qiangshuidiyu.spring.aop;

import org.aopalliance.aop.Advice;
import xin.qiangshuidiyu.spring.aop.aspectj.AspectJExpressionPointcut;
import xin.qiangshuidiyu.spring.aop.aspectj.Pointcut;

/**
 * @author wpy
 * @date 2018/6/25 13:45
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor{
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
