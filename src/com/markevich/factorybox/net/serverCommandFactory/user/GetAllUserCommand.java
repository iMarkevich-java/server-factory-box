package com.markevich.factorybox.net.serverCommandFactory.user;

import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;
import com.markevich.factorybox.service.user.UserDb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUserCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        List<UserDb> listUser = ServiceFactory.UserService().loadAll();
        for (UserDb user : listUser) {
            Map<String, String> map = new HashMap<>();
            map.put("name", user.getName());
            map.put("password", String.valueOf(user.getPasswordDB()));
            response.addResponseData(map);
        }
        response.setResponseCode(ResponseCode.OkCode);
    }
}
