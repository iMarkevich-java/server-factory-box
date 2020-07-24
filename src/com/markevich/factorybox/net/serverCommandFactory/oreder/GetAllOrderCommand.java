package com.markevich.factorybox.net.serverCommandFactory.oreder;

import biznesObgectFactory.Order;
import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllOrderCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        List<Order> listOrder = ServiceFactory.OrderService().loadAll();

        for (Order order : listOrder) {
            Map<String, String> map = new HashMap<>();
            map.put("id", order.getId());
            map.put("sizeOrder", order.getSizeOrder());
            map.put("stage", order.getStage());
            map.put("status", order.getStatus());
            map.put("client-id", order.getClientId());
            map.put("order-name", order.getOrderName());
            map.put("start-date", order.getStartDate());
            map.put("order-term", order.getOrderTerm());

            response.addResponseData(map);
        }
        response.setResponseCode(ResponseCode.OkCode);
    }
}
