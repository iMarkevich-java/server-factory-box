package com.markevich.factorybox.net.interfaces;

public interface Request {

    String getCommandName();

    String getParameter(String paramName);
}
