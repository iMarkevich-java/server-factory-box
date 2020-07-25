package com.markevich.factorybox.net.serverCommandFactory.staffday;

import businessObjectFactoryBox.Day;
import businessObjectFactoryBox.StaffDays;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.math.BigInteger;
import java.time.LocalDate;

public class GetUpdateStaffDayCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String staffId = request.getParameter("staff-id");
        String days = request.getParameter("days");
        String[] daysArray = days.split("#");
        StaffDays staffDay = new StaffDays();
        staffDay.setStaffId(new BigInteger(staffId));
        for (String dayStr : daysArray) {
            String[] dayArray = dayStr.split("/");
            Day day = new Day();
            day.setDay(LocalDate.parse(dayArray[0]));
            day.setOrderName(dayArray[1]);
            day.setProductivity(new BigInteger(dayArray[2]));
            day.setStaffId(new BigInteger(dayArray[3]));
            staffDay.addDay(day);
        }
        ServiceFactory.StaffDayService().update(staffDay);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
