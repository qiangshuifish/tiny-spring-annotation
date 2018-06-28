package xin.qiangshuidiyu.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * @author wpy
 * @date 2018/6/25 13:51
 */
public interface Advisor {

    /**
     * 获取通知器
     * @return
     */
    Advice getAdvice();
}
