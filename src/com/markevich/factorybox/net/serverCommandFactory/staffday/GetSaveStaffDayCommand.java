package com.markevich.factorybox.net.serverCommandFactory.staffday;

import biznesObgectFactory.Day;
import biznesObgectFactory.StaffDays;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.math.BigInteger;
import java.time.LocalDate;

public class GetSaveStaffDayCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String staffId = request.getParameter("staff-id");
        String days = request.getParameter("days");
        StaffDays staffDay = new StaffDays();
        Day day = new Day();
        staffDay.setStaffId(new BigInteger(staffId));
        String[] str = days.split("#");
        for (int i = 0; i < str.length; i++) {
            String dayStr = str[i];
            String[] dayArray = dayStr.split("/");
            day.setDay(LocalDate.parse(dayArray[0]));
            day.setOrderName(dayArray[1]);
            day.setProductivity(new BigInteger(dayArray[2]));
            day.setStaffId(new BigInteger(dayArray[3]));
            staffDay.addDay(day);
        }
        ServiceFactory.StaffDayService().save(staffDay);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
