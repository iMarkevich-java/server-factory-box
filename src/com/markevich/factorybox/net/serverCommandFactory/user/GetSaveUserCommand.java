package com.markevich.factorybox.net.serverCommandFactory.user;

import biznesObgectFactory.User;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetSaveUserCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        ServiceFactory.UserService().save(user);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
