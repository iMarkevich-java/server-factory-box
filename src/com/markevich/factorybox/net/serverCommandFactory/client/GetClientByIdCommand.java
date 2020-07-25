package com.markevich.factorybox.net.serverCommandFactory.client;

import businessObjectFactoryBox.Client;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class GetClientByIdCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String id = request.getParameter("id");

        Client client = ServiceFactory.ClientService().loadById(id);

        Map<String, String> clientMap = new HashMap<>();

        clientMap.put("company-name", client.getCompanyName());
        clientMap.put("legal-data", client.getLegalData());
        clientMap.put("address", client.getAddress());
        clientMap.put("manager", client.getManager());
        clientMap.put("id", client.getId());

        response.addResponseData(clientMap);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
