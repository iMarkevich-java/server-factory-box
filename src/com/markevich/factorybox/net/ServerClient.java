package com.markevich.factorybox.net;

import com.markevich.factorybox.gui.exceptions.ExceptionCreateServerSocket;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClient extends Thread {
    private final Integer countClient = 5;
    private BigInteger port = null;
    private final ArrayList<Socket> socketList = new ArrayList<>();
    private Socket client = null;
    private ServerSocket serverSocket = null;
    private final StopServer stopServer = new StopServer();
    private Boolean stop = true;

    public ServerClient() {
    }

    @Override
    public void run() {
        stop = true;
        try {
            while (stop) {
                serverSocket = new ServerSocket(port.intValue());
                client = serverSocket.accept();
                System.out.println("connect");
                for (int i = 0; i < socketList.size(); i++) {
                    Socket socket = socketList.get(i);
                    if (socket.isClosed()) {
                        socketList.remove(i);
                    }
                }
                if (socketList.size() < countClient) {
                    addSocket(client);
                    ServerMultiClient serverMultiClient = new ServerMultiClient(client);
                    serverMultiClient.setDaemon(true);
                    serverMultiClient.start();
                } else {
                    client.close();
                }
                serverSocket.close();
            }
        } catch (IOException e) {
            throw new ExceptionCreateServerSocket(getClass().getName());
        } catch (ExceptionCreateServerSocket e) {
            throw new ExceptionCreateServerSocket(getClass().getName());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Socket> addSocket(Socket socket) {
        this.socketList.add(socket);
        return this.socketList;
    }

    public void closeSocket() {
        for (int i = 0; i < socketList.size(); i++) {
            try {
                socketList.get(i).close();
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

    public String getPort() {
        return port.toString();
    }

    public void setPort(String port) {
        if (isNumber(port)) {
        }
        this.port = new BigInteger(port);
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}
