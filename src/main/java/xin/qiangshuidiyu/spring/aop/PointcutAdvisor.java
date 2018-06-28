package xin.qiangshuidiyu.spring.aop;

import xin.qiangshuidiyu.spring.aop.aspectj.Pointcut;

/**
 * @author wpy
 * @date 2018/6/25 13:48
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * 获取切点
     * @return
     */
    Pointcut getPointcut();
}
