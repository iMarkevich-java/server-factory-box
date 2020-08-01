package com.markevich.factorybox.gui;

import com.markevich.factorybox.gui.exceptions.CanNotCreateWindowException;
import com.markevich.factorybox.gui.exceptions.ExceptionFindIcon;
import com.markevich.factorybox.gui.exceptions.UnknownWindowException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class AppWindows {

    private static final AppWindows instance = new AppWindows();
    private static Map<WindowConfigs, WindowController> appWindows;
    private Stage primaryStage = null;

    private AppWindows() {
        appWindows = new HashMap<>();
        createWindow(WindowConfigs.ServerWindow);
        createWindow(WindowConfigs.SaveUserWindow);

    }

    public static AppWindows getInstance() {
        return instance;
    }

    public void addMainStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showWindow(WindowConfigs config) {
        WindowController windowController = getWindowByConfig(config);

        try {
            primaryStage.getIcons().add(new Image(getClass().getResource("/resources/icon/icon.jpg").toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new ExceptionFindIcon(getClass().getName());
        }

        primaryStage.setScene(windowController.getScene());
        primaryStage.setTitle(config.getTitle());
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void reloadWindow(WindowConfigs config) {
        WindowController windowController = getWindowByConfig(config);
        windowController.reloadWindow();
    }

    public WindowController getWindowByConfig(WindowConfigs config) {
        WindowController windowController = appWindows.get(config);
        if (windowController == null) {
            throw new UnknownWindowException(config);
        }

        return windowController;
    }

    private void createWindow(WindowConfigs windowConfig) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(windowConfig.getXmlUi()));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(e, windowConfig);
        }
        Scene scene = new Scene(root);

        DBWindow controller = fxmlLoader.getController();

        WindowController windowController = new WindowController(scene, controller);
        appWindows.put(windowConfig, windowController);
    }
}
