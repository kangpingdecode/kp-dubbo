package com.kangping.kpdubbo.server;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: InitalMediator
 * @date 2020/7/5
 */
@Component
public class InitialMediator implements BeanPostProcessor {


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 找到需要发布服务，也就是标注了@KpService服务，找到并保存
        if (bean.getClass().isAnnotationPresent(KpService.class)) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                String key = bean.getClass().getInterfaces()[0].getName() + "." + methods[i].getName();
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(methods[i]);
                Mediator.map.put(key, beanMethod);
            }
        }
        return bean;
    }
}
