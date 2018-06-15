package xin.qiangshuidiyu.spring.aop;

/**
 * 暴露获取代理对象的方法
 * @author wpy
 * @date 2018/6/15 10:29
 */
public interface AopProxy {

    /**
     * 获取代理对象
     * @return
     */
    Object getProxy();
}
