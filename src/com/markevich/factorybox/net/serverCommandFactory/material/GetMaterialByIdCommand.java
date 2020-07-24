package com.markevich.factorybox.net.serverCommandFactory.material;

import biznesObgectFactory.Material;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class GetMaterialByIdCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String id = request.getParameter("id");

        Material material = ServiceFactory.MaterialService().loadById(id);

        Map<String, String> map = new HashMap<>();

        map.put("id", material.getId());
        map.put("supplier-id", material.getSupplierId());
        map.put("material-name", material.getMaterialName());
        map.put("price", material.getPrice());
        map.put("amount", material.getAmount());
        map.put("unit", material.getUnit());
        map.put("size", material.getSize());
        map.put("path-image", material.getPathImage());

        response.addResponseData(map);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
