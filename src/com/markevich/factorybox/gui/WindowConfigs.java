package com.markevich.factorybox.gui;

public enum WindowConfigs {

    ServerWindow("/com/markevich/factorybox/gui/fxml/server.fxml", "Server window"),
    SaveUserWindow("/com/markevich/factorybox/gui/fxml/saveUser.fxml", "Save user window");

    private final String xmlUi;

    private final String title;

    WindowConfigs(String xmlUi, String title) {
        this.xmlUi = xmlUi;
        this.title = title;
    }

    public String getXmlUi() {
        return xmlUi;
    }

    public String getTitle() {
        return title;
    }
}
