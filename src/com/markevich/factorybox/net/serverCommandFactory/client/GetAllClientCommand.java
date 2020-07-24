package com.markevich.factorybox.net.serverCommandFactory.client;

import biznesObgectFactory.Client;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllClientCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        List<Client> listClient = ServiceFactory.ClientService().loadAll();

        for (Client client : listClient) {
            Map<String, String> map = new HashMap<>();
            map.put("company-name", client.getCompanyName());
            map.put("legal-data", client.getLegalData());
            map.put("manager", client.getManager());
            map.put("id", client.getId());
            map.put("address", client.getAddress());

            response.addResponseData(map);
        }
        response.setResponseCode(ResponseCode.OkCode);
    }
}
