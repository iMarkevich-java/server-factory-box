package com.markevich.factorybox.net.interfaces;

import com.markevich.factorybox.net.ResponseCode;
import org.json.JSONWriter;

import java.util.List;
import java.util.Map;

public interface Response {

    void setResponseCode(ResponseCode code);

    int getStatusCode();

    String getStatusMessage();

    void addResponseData(Map<String, String> data);

    List<Map<String, String>> getResponseDataValue();

    JSONWriter getJsonWriter();
}
