package com.markevich.factorybox.net.serverCommandFactory.staffday;

import biznesObgectFactory.Day;
import biznesObgectFactory.StaffDays;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionFindStaffDayId;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class GetStaffDayIdCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        StaffDays staffDay = ServiceFactory.StaffDayService().loadById(request.getParameter("staff-id"));
        try {
            Map<String, String> map = new HashMap<>();
            String days = "";
            map.put("staff-id", staffDay.getStaffId().toString());
            for (Day day : staffDay.getListDay()) {
                days = days + day.getDay().toString() + "/" + day.getOrderName() + "/"
                        + day.getProductivity().toString() + "/" + day.getStaffId().toString() + "/" + "#";
            }
            map.put("days", days);
            response.addResponseData(map);
            response.setResponseCode(ResponseCode.OkCode);
        } catch (NullPointerException nullPointerException) {
            response.setResponseCode(ResponseCode.NotFoundCode);
            new ExceptionFindStaffDayId(getClass().getName());
        }
    }
}
