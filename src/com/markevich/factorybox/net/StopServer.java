package com.markevich.factorybox.net;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

public class StopServer {
    private BigInteger port;

    public void stop(ServerClient serverClient) {
        Socket socket = null;
        serverClient.setStop(false);
        try {
            socket = new Socket("localhost", port.intValue());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPort(String port) {
        this.port = new BigInteger(port);
    }
}
