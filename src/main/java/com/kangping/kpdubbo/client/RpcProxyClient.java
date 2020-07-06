package com.kangping.kpdubbo.client;

import java.lang.reflect.Proxy;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: RpcProxyClient
 * @date 2020/6/28
 */

public class RpcProxyClient {


    public static  <T> T  newInstance(Class<T> interfaces, String host, int prot) {

        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),new Class[]{interfaces},new RemoteInvocationHandler(host,prot));
    }
}
