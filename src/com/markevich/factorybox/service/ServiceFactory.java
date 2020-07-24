package com.markevich.factorybox.service;


import com.markevich.factorybox.service.client.ClientService;
import com.markevich.factorybox.service.connect.ConnectService;
import com.markevich.factorybox.service.material.MaterialService;
import com.markevich.factorybox.service.order.OrderService;
import com.markevich.factorybox.service.product.ProductService;
import com.markevich.factorybox.service.staff.StaffService;
import com.markevich.factorybox.service.staffday.StaffDayService;
import com.markevich.factorybox.service.supplier.SupplierService;
import com.markevich.factorybox.service.user.UserService;


public class ServiceFactory {

    private static final ServiceFactory service = new ServiceFactory();

    public static StaffDayService StaffDayService() {
        return new StaffDayService();
    }

    public static ConnectService ConnectService() {
        return new ConnectService();
    }

    public static UserService UserService() {
        return new UserService();
    }

    public static ClientService ClientService() {
        return new ClientService();
    }

    public static MaterialService MaterialService() {
        return new MaterialService();
    }

    public static OrderService OrderService() {
        return new OrderService();
    }

    public static ProductService ProductService() {
        return new ProductService();
    }

    public static StaffService StaffService() {
        return new StaffService();
    }

    public static SupplierService SupplierService() {
        return new SupplierService();
    }

}
