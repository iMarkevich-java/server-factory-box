package com.markevich.factorybox.net.serverCommandFactory.oreder;

import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetDeleteOrderCommand implements Command {
    @Override
    public void execute(Request request, Response response) {
        String id = request.getParameter("order-id");
        ServiceFactory.OrderService().delete(id);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
