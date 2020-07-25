package com.markevich.factorybox.net.serverCommandFactory.user;

import businessObjectFactoryBox.User;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetVerificationUserCommand implements Command {
    @Override
    public void execute(Request request, Response response) {
        String name = request.getParameter("user-name");
        String password = request.getParameter("user-password");
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        if (ServiceFactory.UserService().verification(user)) {
            response.setResponseCode(ResponseCode.UserAuthorized);
        } else {
            response.setResponseCode(ResponseCode.UserUnauthorized);
        }
    }
}
