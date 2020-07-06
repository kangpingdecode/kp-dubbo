package com.kangping.kpdubbo.client;


import com.kangping.kpdubbo.server.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>
 * 功能： 远程调用的handler
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: RemoteInvcationHandler
 * @date 2020/6/28
 */

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;

    private Integer port;

    public RemoteInvocationHandler(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TransPort transPort = new TransPort(host, port);

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        Class<?>[] parameterTypes = method.getParameterTypes();
        rpcRequest.setTypes(parameterTypes);
        return transPort.send(rpcRequest);
    }
}
