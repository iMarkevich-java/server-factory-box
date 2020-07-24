package com.markevich.factorybox.gui;

import javafx.scene.Scene;

public class WindowController {

    private Scene scene;

    private DBWindow controller;

    public WindowController(Scene scene, DBWindow controller) {
        this.scene = scene;
        this.controller = controller;
    }

    public WindowController(DBWindow controller) {
    }

    public Scene getScene() {
        return scene;
    }

    public DBWindow getController() {
        return controller;
    }

    public void reloadWindow() {
        controller.reloadWindow();
    }
}
