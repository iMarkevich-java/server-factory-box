package com.markevich.factorybox.gui.exceptions;


import com.markevich.factorybox.gui.WindowConfigs;

public class UnknownWindowException extends RuntimeException {

    public UnknownWindowException(WindowConfigs config) {
        super("Can not find window with name - " + config);
    }
}
