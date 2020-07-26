package com.markevich.factorybox.gui.controllers;

import businessObjectFactoryBox.User;
import com.markevich.factorybox.gui.AppWindows;
import com.markevich.factorybox.gui.DBWindow;
import com.markevich.factorybox.gui.WindowConfigs;
import com.markevich.factorybox.service.ServiceFactory;
import com.markevich.factorybox.service.user.UserDb;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class ControllerSaveUserWindow implements DBWindow {


    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label passwordLabel;

    private void showServerWindow() {
        AppWindows.getInstance().showWindow(WindowConfigs.ServerWindow);
        AppWindows.getInstance().reloadWindow(WindowConfigs.ServerWindow);
    }

    @FXML
    private void save() {
        User user = new User();
        if (checkValueText()) {
            List<UserDb> userLis = ServiceFactory.UserService().loadAll();
            for (UserDb userDb : userLis) {
                if (userDb.getName().equals(nameTextField.getText())) {
                    nameLabel.setText("User with this name exists!!!");
                    return;
                } else {
                    nameLabel.setText("");
                }
            }
            user.setName(nameTextField.getText());
            user.setPassword(passwordTextField.getText());
            ServiceFactory.UserService().save(user);
            showServerWindow();
        }
    }

    private Boolean checkValueText() {
        boolean bool = true;

        if (nameTextField.getText().isEmpty()) {
            nameLabel.setText("Please enter text");
            bool = false;
        } else {
            nameLabel.setText("");
        }
        if (passwordTextField.getText().isEmpty()) {
            passwordLabel.setText("Please enter text");
            bool = false;
        } else {
            passwordLabel.setText("");
        }
        return bool;
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
        nameLabel.setText("");
        passwordLabel.setText("");
    }
}
