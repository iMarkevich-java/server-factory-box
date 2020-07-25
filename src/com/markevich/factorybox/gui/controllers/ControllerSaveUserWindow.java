package com.markevich.factorybox.gui.controllers;

import businessObjectFactoryBox.User;
import com.markevich.factorybox.gui.AppWindows;
import com.markevich.factorybox.gui.DBWindow;
import com.markevich.factorybox.gui.WindowConfigs;
import com.markevich.factorybox.service.ServiceFactory;
import com.markevich.factorybox.service.user.UserDb;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;

public class ControllerSaveUserWindow implements DBWindow {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordTextField;

    private void showServerWindow() {
        AppWindows.getInstance().showWindow(WindowConfigs.ServerWindow);
        AppWindows.getInstance().reloadWindow(WindowConfigs.ServerWindow);
    }

    @FXML
    private void save() {
        User user = new User();
        user.setName(nameTextField.getText());
        user.setPassword(passwordTextField.getText());
        List<UserDb> userLis = ServiceFactory.UserService().loadAll();
        if (!(user.getName().isEmpty() || user.getPassword().isEmpty())) {
            for (UserDb userDb : userLis) {
                if (userDb.getName().equals(user.getName())) {
                    return;
                }
            }
            ServiceFactory.UserService().save(user);
            showServerWindow();
        }
    }

    public void back() {
        showServerWindow();
    }

    @Override
    public void setData(String data) {

    }

    @Override
    public void reloadWindow() {
        nameTextField.setText("");
        passwordTextField.setText("");
    }
}
