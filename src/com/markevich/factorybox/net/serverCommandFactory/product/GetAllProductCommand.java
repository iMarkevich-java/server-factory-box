package com.markevich.factorybox.net.serverCommandFactory.product;

import biznesObgectFactory.Product;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllProductCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        List<Product> listProduct = ServiceFactory.ProductService().loadAll();

        for (Product product : listProduct) {
            Map<String, String> map = new HashMap<>();
            map.put("id", product.getId());
            map.put("color", product.getColor());
            map.put("material", product.getMaterial());
            map.put("image", product.getImage());
            map.put("texture", product.getTexture());
            map.put("order-id", product.getOrderId());
            map.put("size-box", product.getSizeBox());

            response.addResponseData(map);
        }
        response.setResponseCode(ResponseCode.OkCode);
    }
}
