package design.uranus.study.annotation.bean;

import java.lang.reflect.Method;

/**
 * Created by zhanghua.luo on 12/30/16.
 * 存放RequestMapping注解方法
 */
public class ExecutorBean {
    private Object object;
    private Method method;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
