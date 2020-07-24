package com.markevich.factorybox.net.serverCommandFactory.staff;

import biznesObgectFactory.Staff;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetSaveStaffCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String department = request.getParameter("department");
        String position = request.getParameter("position");
        String address = request.getParameter("address");
        String lastName = request.getParameter("last-name");
        String firstName = request.getParameter("first-name");
        String salary = request.getParameter("salary");
        String dateOfBirth = request.getParameter("date-of-birth");
        String pathPhoto = request.getParameter("path-photo");
        String id = request.getParameter("id");

        Staff staff = new Staff();
        staff.setPosition(position);
        staff.setPathPhoto(pathPhoto);
        staff.setId(id);
        staff.setAddress(address);
        staff.setDepartment(department);
        staff.setDateOfBirth(dateOfBirth);
        staff.setLastName(lastName);
        staff.setFirstName(firstName);
        staff.setSalary(salary);


        ServiceFactory.StaffService().save(staff);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
