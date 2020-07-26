package com.markevich.factorybox.net;

import com.markevich.factorybox.net.interfaces.Response;
import org.json.JSONWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SocketJsonResponse implements Response {

    private final List<Map<String, String>> responseData = new ArrayList<>();
    private final JSONWriter writer;
    private int statusCode;
    private String statusMessage;

    public SocketJsonResponse(JSONWriter writer) {
        this.writer = writer;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setResponseCode(ResponseCode code) {
        this.statusCode = code.getCode();
        this.statusMessage = code.getMessage();
    }

    @Override
    public void addResponseData(Map<String, String> data) {
        this.responseData.add(data);
    }

    @Override
    public List<Map<String, String>> getResponseDataValue() {
        return responseData;
    }

    @Override
    public JSONWriter getJsonWriter() {
        return writer;
    }
}
