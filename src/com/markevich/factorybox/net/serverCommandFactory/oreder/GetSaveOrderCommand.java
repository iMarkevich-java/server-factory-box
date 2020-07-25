package com.markevich.factorybox.net.serverCommandFactory.oreder;

import businessObjectFactoryBox.Order;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetSaveOrderCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String sizeOrder = request.getParameter("size-order");
        String status = request.getParameter("status");
        String stage = request.getParameter("stage");
        String id = request.getParameter("id");
        String clientId = request.getParameter("client-id");
        String orderName = request.getParameter("order-name");
        String orderTerm = request.getParameter("order-term");
        String startDate = request.getParameter("start-date");

        Order order = new Order();
        order.setId(id);
        order.setSizeOrder(sizeOrder);
        order.setStatus(status);
        order.setStage(stage);
        order.setClientId(clientId);
        order.setOrderName(orderName);
        order.setOrderTerm(orderTerm);
        order.setStartDate(startDate);

        ServiceFactory.OrderService().save(order);
        response.setResponseCode(ResponseCode.OkCode);


    }
}
