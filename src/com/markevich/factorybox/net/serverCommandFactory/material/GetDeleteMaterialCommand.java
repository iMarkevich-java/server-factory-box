package com.markevich.factorybox.net.serverCommandFactory.material;

import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetDeleteMaterialCommand implements Command {
    @Override
    public void execute(Request request, Response response) {
        String id = request.getParameter("id");
        ServiceFactory.MaterialService().delete(id);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
