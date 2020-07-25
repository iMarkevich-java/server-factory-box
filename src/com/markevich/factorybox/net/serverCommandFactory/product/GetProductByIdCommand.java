package com.markevich.factorybox.net.serverCommandFactory.product;

import businessObjectFactoryBox.Product;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class GetProductByIdCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String id = request.getParameter("id");

        Product product = ServiceFactory.ProductService().loadById(id);

        Map<String, String> map = new HashMap<>();

        map.put("id", product.getId());
        map.put("color", product.getColor());
        map.put("material", product.getMaterial());
        map.put("image", product.getImage());
        map.put("texture", product.getTexture());
        map.put("order-id", product.getOrderId());
        map.put("size-box", product.getSizeBox());

        response.addResponseData(map);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
