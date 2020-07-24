package com.markevich.factorybox.net.serverCommandFactory.product;

import biznesObgectFactory.Product;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetSaveProductCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String material = request.getParameter("material");
        String image = request.getParameter("image");
        String texture = request.getParameter("texture");
        String color = request.getParameter("color");
        String sizeBox = request.getParameter("size-box");
        String orderId = request.getParameter("order-id");
        String id = request.getParameter("id");

        Product product = new Product();
        product.setId(id);
        product.setMaterial(material);
        product.setImage(image);
        product.setTexture(texture);
        product.setColor(color);
        product.setSizeBox(sizeBox);
        product.setOrderId(orderId);

        ServiceFactory.ProductService().save(product);
        response.setResponseCode(ResponseCode.OkCode);


    }
}
