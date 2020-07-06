package com.kangping.kpdubbo.server;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: Mediator
 * @date 2020/7/5
 */

public class Mediator {

    // 保存需要发布的对象信息
    public static ConcurrentMap<String,BeanMethod> map = new ConcurrentHashMap();

    private static volatile Mediator mediator;

    private Mediator(){}

    public static Mediator getInstance() {
        if (mediator == null) {
            synchronized (Mediator.class) {
                if (mediator == null) {
                    mediator = new Mediator();
                }
            }
        }
        return mediator;
    }

    public Object Processor(RpcRequest request) {
        String key = request.getClassName() + "." + request.getMethodName();
        BeanMethod beanMethod = map.get(key);
        if (beanMethod == null) {
            return null;
        }
        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();
        try {
           return method.invoke(bean, request.getParameters());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


}
