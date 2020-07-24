package com.markevich.factorybox.net.serverCommandFactory.supplier;

import biznesObgectFactory.Supplier;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetSaveSupplierCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String companyName = request.getParameter("company-name");
        String address = request.getParameter("address");
        String legalData = request.getParameter("legal-data");
        String manager = request.getParameter("manager");
        String id = request.getParameter("id");
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setCompanyName(companyName);
        supplier.setAddress(address);
        supplier.setLegalData(legalData);
        supplier.setManager(manager);
        ServiceFactory.SupplierService().save(supplier);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
