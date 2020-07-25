package com.markevich.factorybox.net.serverCommandFactory.staffday;

import businessObjectFactoryBox.Day;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.math.BigInteger;
import java.time.LocalDate;

public class GetDeleteDayCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String days = request.getParameter("days");
        Day day = new Day();
        String[] dayArray = days.split("/");
        day.setDay(LocalDate.parse(dayArray[0]));
        day.setOrderName(dayArray[1]);
        day.setProductivity(new BigInteger(dayArray[2]));
        day.setStaffId(new BigInteger(dayArray[3]));
        ServiceFactory.StaffDayService().deleteDay(day);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
