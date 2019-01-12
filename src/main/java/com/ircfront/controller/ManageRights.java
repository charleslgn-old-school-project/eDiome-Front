package com.ircfront.controller;

import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.ircserv.inter.ServerInterface;
import com.ircserv.metier.Utilisateur;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.rmi.Naming;
import java.util.List;
import java.util.ResourceBundle;

public class ManageRights implements Initializable {

    private int nbServ;
    @FXML
    VBox vbox;
    List<Utilisateur> users;


    public ManageRights(int nbServ) {
        this.nbServ = nbServ;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServerConstante.SERVER = (ServerInterface) Naming.lookup("//" + ServerConstante.IP + ":" + ServerConstante.PORT + "/serv" + nbServ);
            String color = XMLDataFinder.getTheme();
            vbox.getStylesheets().add(getClass().getResource("../../../gui/css/main-" + color + ".css").toExternalForm());
            vbox.setPadding(new Insets(10, 10, 10, 10));
            vbox.setSpacing(10);
            vbox.setAlignment(Pos.TOP_LEFT);
            vbox.setPrefSize(400,400);
            vbox.setMaxSize(400,400);

            VBox vboxbottom = new VBox();
            vboxbottom.setSpacing(10);
            vboxbottom.setPadding(new Insets(10, 10, 10, 10));
            vboxbottom.setAlignment(Pos.BOTTOM_CENTER);

            VBox vboxdata = new VBox();
            vboxbottom.setSpacing(10);

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            vboxdata.setAlignment(Pos.TOP_LEFT);
            scrollPane.getStyleClass().add("menu-bar-2");

            users = ServerConstante.SERVER.getAllUserInServer();
            for (Utilisateur user : users) {
                String identite = user.getPrenom() + " " + user.getNom();
                JFXComboBox jfxComboBox = new JFXComboBox();
                jfxComboBox.setItems(FXCollections.observableArrayList("Administrateur", "Modérateur", "Utilisateur"));
                HBox hbox = new HBox(jfxComboBox, new Label(identite));
                hbox.setSpacing(20);
                hbox.setAlignment(Pos.TOP_LEFT);
                vboxdata.getChildren().add(hbox);
            }

            scrollPane.setContent(vboxdata);

            JFXButton add = new JFXButton("Confirmer");
            add.setButtonType(JFXButton.ButtonType.FLAT);
            add.getStyleClass().add("addserverbutton");
            add.setPrefSize(200, 50);
            add.setMinSize(200,50);
            vboxbottom.getChildren().add(add);


            add.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // confirmer
                }
            });

            // Bouton pour fermer la popup
            JFXButton annuler = new JFXButton("Annuler");
            annuler.setButtonType(JFXButton.ButtonType.FLAT);
            annuler.getStyleClass().add("addserverbutton");
            annuler.setPrefSize(200, 50);
            annuler.setMinSize(200, 50);
            vboxbottom.getChildren().add(annuler);

            annuler.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Stage stage = (Stage) annuler.getScene().getWindow();
                    stage.close();
                }
            });
            vbox.getChildren().add(scrollPane);
            vbox.getChildren().add(vboxbottom);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
