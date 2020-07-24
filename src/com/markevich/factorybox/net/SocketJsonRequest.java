package com.markevich.factorybox.net;

import com.markevich.factorybox.net.interfaces.Request;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SocketJsonRequest implements Request {

    private String commandName;

    private final Map<String, String> parameters = new HashMap<>();

    public SocketJsonRequest(JSONTokener jsonReader) {
        try {
            JSONObject request = (JSONObject) jsonReader.nextValue();

            JSONObject headers = request.getJSONObject("headers");
            this.commandName = headers.getString("command-name");

            JSONObject parameters = request.getJSONObject("parameters");
            Set<String> paramNames = parameters.keySet();
            for (String paramName : paramNames) {
                String paramValue = parameters.getString(paramName);
                this.parameters.put(paramName, paramValue);
            }
        } catch (JSONException e) {
            this.commandName = "verification-connect";
        }


    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getParameter(String paramName) {
        return parameters.get(paramName);
    }
}
