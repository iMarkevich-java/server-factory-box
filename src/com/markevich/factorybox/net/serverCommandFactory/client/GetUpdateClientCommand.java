package com.markevich.factorybox.net.serverCommandFactory.client;

import biznesObgectFactory.Client;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetUpdateClientCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String companyName = request.getParameter("companyName");
        String address = request.getParameter("address");
        String legalData = request.getParameter("legalData");
        String manager = request.getParameter("manager");
        String id = request.getParameter("id");

        Client client = new Client();
        client.setCompanyName(companyName);
        client.setAddress(address);
        client.setLegalData(legalData);
        client.setManager(manager);
        client.setId(id);

        ServiceFactory.ClientService().update(client);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
