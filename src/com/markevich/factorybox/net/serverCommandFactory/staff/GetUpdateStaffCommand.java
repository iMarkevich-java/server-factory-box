package com.markevich.factorybox.net.serverCommandFactory.staff;

import businessObjectFactoryBox.Staff;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetUpdateStaffCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String address = request.getParameter("address");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String dateOfBirth = request.getParameter("date-of-birth");
        String department = request.getParameter("department");
        String salary = request.getParameter("salary");
        String id = request.getParameter("id");
        String position = request.getParameter("position");
        String pathPhoto = request.getParameter("path-photo");

        Staff staff = new Staff();
        staff.setId(id);
        staff.setAddress(address);
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setDateOfBirth(dateOfBirth);
        staff.setSalary(salary);
        staff.setDepartment(department);
        staff.setPosition(position);
        staff.setPathPhoto(pathPhoto);

        ServiceFactory.StaffService().update(staff);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
