package com.markevich.factorybox.net.serverCommandFactory.staffday;

import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetDeleteStaffDayCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String staffId = request.getParameter("staff-id");
        ServiceFactory.StaffDayService().delete(staffId);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
