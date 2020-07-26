package com.markevich.factorybox.gui.controllers;

import com.markevich.factorybox.gui.AppWindows;
import com.markevich.factorybox.gui.DBWindow;
import com.markevich.factorybox.gui.WindowConfigs;
import com.markevich.factorybox.net.ServerClient;
import com.markevich.factorybox.net.StopServer;
import com.markevich.factorybox.service.ServiceFactory;
import com.markevich.factorybox.service.user.UserDb;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigInteger;
import java.util.List;

public class ControllerMainWindow implements DBWindow {

    @FXML
    private Button deleteUserButton;
    @FXML
    private TextField port;
    @FXML
    private Button run;
    @FXML
    private TextArea text;
    private ServerClient serverClient;
    @FXML
    private TableView<UserDb> userDB;

    public void run() {
        if (port.getText().equals("")) {
            text.setText("ENTER NUMBER PORT");
        } else {
            if (run.getText().equals("RUN")) {

                serverClient = new ServerClient();
                if (isNumber(port.getText())) {
                    serverClient.setPort(port.getText());
                } else {
                    text.setText("Enter number value for port!!!");
                    return;
                }
                run.setText("STOP");
                serverClient.setDaemon(true);
                serverClient.start();
                text.setText("SERVER RUN");
            } else {
                run.setText("RUN");
                text.setText("SERVER STOP");
                StopServer stopServer = new StopServer();
                stopServer.setPort(port.getText());
                stopServer.stop(serverClient);
                serverClient.closeSocket();
                serverClient.closeServer();
            }
        }
    }

    public void checkUser() {
        UserDb userDb = userDB.getSelectionModel().getSelectedItem();
        if(!(userDb == null)) {
            deleteUserButton.setDisable(false);
        }
    }

    public void deleteUser() {
        UserDb userDb = userDB.getSelectionModel().getSelectedItem();
        if (!(userDb == null)) {
            ServiceFactory.UserService().delete(userDb.getName());
            reloadWindow();
        }
    }

    public void addUser() {
        AppWindows.getInstance().showWindow(WindowConfigs.SaveUserWindow);
        AppWindows.getInstance().reloadWindow(WindowConfigs.SaveUserWindow);

    }

    private Boolean isNumber(String strNumber) {
        try {
            new BigInteger(strNumber);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public void exit() {
        Platform.exit();
    }

    @Override
    public void setData(String data) {

    }

    @Override
    public void reloadWindow() {
        deleteUserButton.setDisable(true);
        ObservableList<UserDb> observableList = userDB.getItems();
        List<UserDb> userList = ServiceFactory.UserService().loadAll();
        observableList.clear();
        observableList.addAll(userList);

    }
}
