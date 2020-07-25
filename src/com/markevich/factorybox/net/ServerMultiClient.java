package com.markevich.factorybox.net;

import businessObjectFactoryBox.Client;
import com.markevich.factorybox.net.exeptions.*;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.net.serverCommandFactory.CommandFactory;
import com.markevich.factorybox.net.serverCommandFactory.exceptions.ExceptionFlushOutputStream;
import org.json.JSONTokener;
import org.json.JSONWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServerMultiClient extends Thread {
    private InputStream inputStream;
    private OutputStreamWriter outputStream;
    private JSONTokener jsonReader;
    private JSONWriter jsonWriter;
    private List<Client> clientList;
    private final Socket socket;
    private Request request;
    private Response response;

    public ServerMultiClient(Socket socket) {
        this.socket = socket;
    }

    private static void buildHeadersSection(JSONWriter jsonWriter, Response response) {
        jsonWriter.key("headers");
        jsonWriter.object();
        jsonWriter.key("status-code").value(response.getStatusCode());
        jsonWriter.key("status-message").value(response.getStatusMessage());
        jsonWriter.endObject();
    }

    private static void buildResponseDataSection(JSONWriter jsonWriter, Response response) {
        List<Map<String, String>> responseDataValue = response.getResponseDataValue();
        jsonWriter.key("response-data");
        jsonWriter.array();
        for (Map<String, String> dataValue : responseDataValue) {
            jsonWriter.object();
            Set<String> keySet = dataValue.keySet();
            for (String key : keySet) {
                jsonWriter.key(key).value(dataValue.get(key));
            }
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
    }

    @Override
    public void run() {
        synchronized (socket) {
            try {
                if (socket.isBound()) {
                    inputStream = inputStreamBuilder(socket);
                    JSONTokener jsonReader = new JSONTokener(inputStream);
                    request = new SocketJsonRequest(jsonReader);
                    outputStream = outputStreamBuilder(socket);
                    JSONWriter jsonWriter = new JSONWriter(outputStream);
                    response = new SocketJsonResponse(jsonWriter);
                    CommandFactory commandFactory = CommandFactory.get();
                    Command command = commandFactory.getCommand(request.getCommandName());
                    if (command == null) {
                        response.setResponseCode(ResponseCode.NotFoundCode);
                        jsonWriter.object();
                        buildHeadersSection(jsonWriter, response);
                        buildResponseDataSection(jsonWriter, response);
                        jsonWriter.endObject();
                        flushOutputStream();
                    }
                    if (!(command == null)) {
                        command.execute(request, response);
                    }
                    jsonWriter.object();
                    buildHeadersSection(jsonWriter, response);
                    buildResponseDataSection(jsonWriter, response);
                    jsonWriter.endObject();
                    flushOutputStream();
                }
                closeStream(outputStream, inputStream, socket);
            } catch (ExceptionCreateInputStream | ExceptionCreateOutputStream input) {
                new ExceptionCreateStream(getClass().getName());
            }
        }
    }

    private void closeStream(OutputStreamWriter outputStreamWriter, InputStream inputStream, Socket socket) {
        try {
            if (!(outputStreamWriter == null)) {
                outputStreamWriter.close();
            }
        } catch (IOException e) {
            throw new ExceptionCloseInputStream();
        }
        try {
            if (!(inputStream == null)) {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new ExceptionCloseOutputStream();
        }
        try {
            if (!(socket == null)) {
                socket.close();
            }
        } catch (IOException e) {
            throw new ExceptionCloseSocket(getClass().getName());
        }

    }

    private void flushOutputStream() {
        try {
            outputStream.flush();
        } catch (IOException e) {
            throw new ExceptionFlushOutputStream(getClass().getName());
        }
    }

    private OutputStreamWriter outputStreamBuilder(Socket socket) {
        OutputStreamWriter outputStream;
        try {
            outputStream = new OutputStreamWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new ExceptionCreateOutputStream();
        }
        return outputStream;
    }

    private InputStream inputStreamBuilder(Socket socket) {
        InputStream inputStream;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            throw new ExceptionCreateInputStream(getClass().getName());
        }
        return inputStream;
    }
}