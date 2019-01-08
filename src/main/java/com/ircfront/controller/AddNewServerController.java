package com.ircfront.controller;
import com.ircfront.start.Main;
import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.ircserv.inter.MenuInterface;
import com.ircserv.metier.Server;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
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

    public AddNewServerController(int userId){
        this.userId = userId;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();
            }
        });

        String color = XMLDataFinder.getTheme();
        vbox.getStylesheets().add(getClass().getResource("../../../gui/css/main-" + color + ".css").toExternalForm());
    }

    @FXML
    void addserver(ActionEvent event) {
        try {
            Server serv = ServerConstante.MENU.createNewServer(serverName.getText(), userId);
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
