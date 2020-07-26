package com.markevich.factorybox.net.serverCommandFactory;

import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.serverCommandFactory.client.*;
import com.markevich.factorybox.net.serverCommandFactory.connect.GetVerificationConnectCommand;
import com.markevich.factorybox.net.serverCommandFactory.material.*;
import com.markevich.factorybox.net.serverCommandFactory.oreder.*;
import com.markevich.factorybox.net.serverCommandFactory.product.*;
import com.markevich.factorybox.net.serverCommandFactory.staff.*;
import com.markevich.factorybox.net.serverCommandFactory.staffday.*;
import com.markevich.factorybox.net.serverCommandFactory.supplier.GetAllSupplierCommand;
import com.markevich.factorybox.net.serverCommandFactory.supplier.GetDeleteSupplierCommand;
import com.markevich.factorybox.net.serverCommandFactory.supplier.GetSaveSupplierCommand;
import com.markevich.factorybox.net.serverCommandFactory.supplier.GetUpdateSupplierCommand;
import com.markevich.factorybox.net.serverCommandFactory.user.GetVerificationUserCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final CommandFactory instance = new CommandFactory();

    private final Map<String, Command> commands = new HashMap<>();

    public CommandFactory() {

        commands.put("get-delete-staff-day", new GetDeleteDayCommand());
        commands.put("get-staff-day-by-id", new GetStaffDayIdCommand());
        commands.put("get-all-staff-day", new GetAllStaffDayCommand());
        commands.put("get-delete-staff-days", new GetDeleteStaffDayCommand());
        commands.put("update-staff-day", new GetUpdateStaffDayCommand());
        commands.put("save-staff-day", new GetSaveStaffDayCommand());
        commands.put("verification-connect", new GetVerificationConnectCommand());
        commands.put("verification-user", new GetVerificationUserCommand());
        commands.put("get-all-material", new GetAllMaterialCommand());
        commands.put("delete-material", new GetDeleteMaterialCommand());
        commands.put("get-material-by-id", new GetMaterialByIdCommand());
        commands.put("save-material", new GetSaveMaterialCommand());
        commands.put("update-material", new GetUpdateMaterialCommand());
        commands.put("get-all-client", new GetAllClientCommand());
        commands.put("delete-client", new GetDeleteClientCommand());
        commands.put("get-client-by-id", new GetClientByIdCommand());
        commands.put("save-client", new GetSaveClientCommand());
        commands.put("update-client", new GetUpdateClientCommand());
        commands.put("get-all-order", new GetAllOrderCommand());
        commands.put("delete-order", new GetDeleteOrderCommand());
        commands.put("save-order", new GetSaveOrderCommand());
        commands.put("update-order", new GetUpdateOrderCommand());
        commands.put("get-order-by-id", new GetOrderByIdCommand());
        commands.put("get-staff-by-id", new GetStaffByIdCommand());
        commands.put("get-all-staff", new GetAllStaffCommand());
        commands.put("delete-staff", new GetDeleteStaffCommand());
        commands.put("update-staff", new GetUpdateStaffCommand());
        commands.put("save-staff", new GetSaveStaffCommand());
        commands.put("get-all-supplier", new GetAllSupplierCommand());
        commands.put("delete-supplier", new GetDeleteSupplierCommand());
        commands.put("update-supplier", new GetUpdateSupplierCommand());
        commands.put("save-supplier", new GetSaveSupplierCommand());
        commands.put("get-all-product", new GetAllProductCommand());
        commands.put("get-product-by-id", new GetProductByIdCommand());
        commands.put("delete-product", new GetDeleteProductCommand());
        commands.put("save-product", new GetSaveProductCommand());
        commands.put("update-product", new GetUpdateProductCommand());
    }

    public static CommandFactory get() {
        return instance;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
