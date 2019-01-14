package com.ircfront.controller;

import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AddNewServerController implements Initializable {

    @FXML
    private JFXTextField serverName;

    @FXML
    private JFXButton addServerButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    private VBox vbox;

    private int userId;

    AddNewServerController(int userId){
        this.userId = userId;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        closeButton.setOnAction(e -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });
        addServerButton.getStyleClass().add("button");
        closeButton.getStyleClass().add("button");

        String color = XMLDataFinder.getTheme();
        vbox.getStylesheets().add(getClass().getResource("/gui/css/main-" + color + ".css").toExternalForm());
    }

    @FXML
    void addserver(ActionEvent event) {
        try {
            ServerConstante.MENU.createNewServer(serverName.getText(), userId);
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
