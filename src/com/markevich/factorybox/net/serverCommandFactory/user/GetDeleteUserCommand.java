package com.markevich.factorybox.net.serverCommandFactory.user;

import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetDeleteUserCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String name = request.getParameter("user-name");
        ServiceFactory.UserService().delete(name);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
