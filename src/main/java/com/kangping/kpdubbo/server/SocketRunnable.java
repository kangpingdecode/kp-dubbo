package com.kangping.kpdubbo.server;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: SocketRunable
 * @date 2020/6/28
 */

public class SocketRunnable implements Runnable {

    private Socket socket;

    public SocketRunnable(Socket socket) {
        this.socket = socket;
    }

    public void run() {


        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Mediator mediator = Mediator.getInstance();
            Object object = mediator.Processor(rpcRequest);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
                if (objectInputStream != null)
                    objectInputStream.close();
                if (objectOutputStream != null)
                    objectOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
