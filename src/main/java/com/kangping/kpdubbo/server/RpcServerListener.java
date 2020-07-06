package com.kangping.kpdubbo.server;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: RpcServerLisenter
 * @date 2020/7/5
 */
@Component
public class RpcServerListener implements ApplicationListener<ApplicationEvent> {

    private int port;


    public static ExecutorService executorService = Executors.newFixedThreadPool(20);

    public void onApplicationEvent(ApplicationEvent applicationEvent) {

            ServerSocket serverSocket = null;
            Socket socket = null;
            try {
                serverSocket = new ServerSocket(port);
                while (true) {
                    socket = serverSocket.accept();
                    executorService.submit(new SocketRunnable(socket));
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (serverSocket != null)
                        serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
