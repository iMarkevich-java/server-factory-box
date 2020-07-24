package com.markevich.factorybox.net.serverCommandFactory.staff;

import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetDeleteStaffCommand implements Command {
    @Override
    public void execute(Request request, Response response) {
        String id = request.getParameter("staff-id");
        ServiceFactory.StaffService().delete(id);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
