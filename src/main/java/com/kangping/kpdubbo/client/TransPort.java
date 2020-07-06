package com.kangping.kpdubbo.client;


import com.kangping.kpdubbo.server.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <p>
 * 功能： 远程连接类
 *
 * 定义了需要调用的远程的端口和ip
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: TransPort
 * @date 2020/6/28
 */

public class TransPort {

    private String host;

    private Integer port;

    public TransPort(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public Socket getSocket() throws IOException {
        Socket socket = new Socket(host, port);
        return socket;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Object send(RpcRequest request) {
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            socket = getSocket();
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            Object object = inputStream.readObject();
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket!=null)
                socket.close();
                if (inputStream!=null)
                inputStream.close();
                if (outputStream!=null)
                    outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
