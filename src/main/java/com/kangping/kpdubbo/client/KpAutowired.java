package com.kangping.kpdubbo.client;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: KpAutoware
 * @date 2020/7/5
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface KpAutowired {

}
