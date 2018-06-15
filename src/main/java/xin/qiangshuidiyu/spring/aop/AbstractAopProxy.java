package xin.qiangshuidiyu.spring.aop;

/**
 * @author wpy
 * @date 2018/6/15 10:43
 */
abstract public class AbstractAopProxy implements AopProxy{
    protected AdvisedSupport advisedSupport;

    public AbstractAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
}
