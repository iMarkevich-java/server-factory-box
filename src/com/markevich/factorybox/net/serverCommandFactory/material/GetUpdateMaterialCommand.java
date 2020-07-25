package com.markevich.factorybox.net.serverCommandFactory.material;

import businessObjectFactoryBox.Material;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetUpdateMaterialCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String id = request.getParameter("id");
        String supplierId = request.getParameter("supplier-id");
        String materialName = request.getParameter("material-name");
        String price = request.getParameter("price");
        String amount = request.getParameter("amount");
        String unit = request.getParameter("unit");
        String size = request.getParameter("size");
        String pathImage = request.getParameter("path-image");

        Material material = new Material();
        material.setId(id);
        material.setSupplierId(supplierId);
        material.setMaterialName(materialName);
        material.setPrice(price);
        material.setAmount(amount);
        material.setUnit(unit);
        material.setSize(size);
        material.setPathImage(pathImage);

        ServiceFactory.MaterialService().update(material);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
