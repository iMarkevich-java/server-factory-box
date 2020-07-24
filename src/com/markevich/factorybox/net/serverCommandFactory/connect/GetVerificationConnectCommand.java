package com.markevich.factorybox.net.serverCommandFactory.connect;

import com.markevich.factorybox.net.ResponseCode;
import com.markevich.factorybox.net.interfaces.Command;
import com.markevich.factorybox.net.interfaces.Request;
import com.markevich.factorybox.net.interfaces.Response;
import com.markevich.factorybox.service.ServiceFactory;

public class GetVerificationConnectCommand implements Command {
    @Override
    public void execute(Request request, Response response) {


        if (ServiceFactory.ConnectService().verificationConnect()) {
            response.setResponseCode(ResponseCode.OkCode);
        } else {
            response.setResponseCode(ResponseCode.ServerErrorCode);
        }
    }
}
