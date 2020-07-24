package com.markevich.factorybox.net.serverCommandFactory.product;

import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetDeleteProductCommand implements Command {
    @Override
    public void execute(Request request, Response response) {
        String id = request.getParameter("product-id");
        ServiceFactory.ProductService().delete(id);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
