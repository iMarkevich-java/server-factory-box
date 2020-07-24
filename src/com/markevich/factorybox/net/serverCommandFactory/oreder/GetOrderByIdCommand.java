package com.markevich.factorybox.net.serverCommandFactory.oreder;

import biznesObgectFactory.Order;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class GetOrderByIdCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String id = request.getParameter("id");

        Order order = ServiceFactory.OrderService().loadById(id);

        Map<String, String> orderMap = new HashMap<>();

        orderMap.put("client-id", order.getClientId());
        orderMap.put("stage", order.getStage());
        orderMap.put("status", order.getStatus());
        orderMap.put("size-order", order.getSizeOrder());
        orderMap.put("order-name", order.getOrderName());
        orderMap.put("order-term", order.getOrderTerm());
        orderMap.put("start-date", order.getStartDate());
        orderMap.put("id", order.getId());

        response.addResponseData(orderMap);
        response.setResponseCode(ResponseCode.OkCode);
    }
}
