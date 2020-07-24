package com.markevich.factorybox.gui.exceptions;

import com.markevich.factorybox.gui.WindowConfigs;

import java.io.IOException;

public class CanNotCreateWindowException extends RuntimeException {

    public CanNotCreateWindowException(Throwable cause, WindowConfigs windowName) {
        super("Can not create Window: " + windowName, cause);
    }

    public CanNotCreateWindowException(IOException e, WindowConfigs windowConfig) {

    }
}
