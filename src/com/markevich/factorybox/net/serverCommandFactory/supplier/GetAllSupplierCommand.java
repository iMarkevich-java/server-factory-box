package com.markevich.factorybox.net.serverCommandFactory.supplier;

import biznesObgectFactory.Supplier;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllSupplierCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        List<Supplier> listSupplier = ServiceFactory.SupplierService().loadAll();
        for (Supplier supplier : listSupplier) {
            Map<String, String> map = new HashMap<>();
            map.put("company-name", supplier.getCompanyName());
            map.put("legal-data", supplier.getLegalData());
            map.put("manager", supplier.getManager());
            map.put("id", supplier.getId());
            map.put("address", supplier.getAddress());
            response.addResponseData(map);
        }
        response.setResponseCode(ResponseCode.OkCode);
    }
}
