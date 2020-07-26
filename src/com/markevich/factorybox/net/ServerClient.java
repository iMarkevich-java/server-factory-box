package com.markevich.factorybox.net;

import com.markevich.factorybox.gui.exceptions.ExceptionCreateServerSocket;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClient extends Thread {
    private final ArrayList<Socket> socketList = new ArrayList<>();
    private BigInteger port = null;
    private ServerSocket serverSocket = null;
    private Boolean stop = true;

    public ServerClient() {
    }

    @Override
    public void run() {
        stop = true;
        try {
            while (stop) {
                serverSocket = new ServerSocket(port.intValue());
                Socket client = serverSocket.accept();
                System.out.println("connect");
                addSocket(client);
                ServerMultiClient serverMultiClient = new ServerMultiClient(client);
                serverMultiClient.setDaemon(true);
                serverMultiClient.start();
                serverSocket.close();
            }
        } catch (IOException | ExceptionCreateServerSocket e) {
            throw new ExceptionCreateServerSocket(getClass().getName());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void addSocket(Socket socket) {
        this.socketList.add(socket);
    }

    public void closeSocket() {
        for (Socket socket : socketList) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("error socket close");
            }
        }
    }

    public void closeServer() {
        if (!(serverSocket == null)) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean isNumber(String strNumber) {
        try {
            new BigInteger(strNumber);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public void setPort(String port) {
        if (isNumber(port)) {
            this.port = new BigInteger(port);
        }
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}
