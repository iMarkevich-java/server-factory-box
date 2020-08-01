package com.markevich.factorybox;


import com.markevich.factorybox.gui.AppWindows;
import com.markevich.factorybox.gui.WindowConfigs;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AppWindows.getInstance().addMainStage(primaryStage);
        AppWindows.getInstance().showWindow(WindowConfigs.ServerWindow);
        AppWindows.getInstance().reloadWindow(WindowConfigs.ServerWindow);
    }
}
