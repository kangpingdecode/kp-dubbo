package com.kangping.kpdubbo.server;

import java.lang.reflect.Method;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: BeanMethod
 * @date 2020/7/5
 */

public class BeanMethod {

    private Object bean;

    private Method method;


    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
