package com.markevich.factorybox.net.serverCommandFactory.staff;

import biznesObgectFactory.Staff;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllStaffCommand implements Command {
    @Override
    public void execute(Request request, Response response) {
        List<Staff> listStaff = ServiceFactory.StaffService().loadAll();

        for (Staff staff : listStaff) {
            Map<String, String> map = new HashMap<>();
            map.put("id", staff.getId());
            map.put("first-name", staff.getFirstName());
            map.put("last-name", staff.getLastName());
            map.put("date-of-birth", staff.getDateOfBirth());
            map.put("salary", staff.getSalary());
            map.put("position", staff.getPosition());
            map.put("address", staff.getAddress());
            map.put("department", staff.getDepartment());
            map.put("path-photo", staff.getPathPhoto());

            response.addResponseData(map);
        }
        response.setResponseCode(ResponseCode.OkCode);
    }
}
