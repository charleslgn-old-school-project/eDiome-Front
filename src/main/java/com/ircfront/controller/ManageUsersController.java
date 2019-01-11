package com.ircfront.controller;

import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.ircserv.inter.ServerInterface;
import com.ircserv.metier.Utilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.Naming;
import java.util.List;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {

    @FXML
    private VBox vbox;

    private int nbServ;
    private boolean typeChoix;

    public ManageUsersController(int nbServ, boolean typeChoix) {
        this.nbServ = nbServ;
        this.typeChoix = typeChoix;
    }

    List<Utilisateur> users;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServerConstante.SERVER = (ServerInterface) Naming.lookup("//" + ServerConstante.IP + ":" + ServerConstante.PORT + "/serv" + nbServ);
            // Style
            String color = XMLDataFinder.getTheme();
            vbox.getStylesheets().add(getClass().getResource("../../../gui/css/main-" + color + ".css").toExternalForm());
            System.out.println(this.nbServ);

            vbox.setSpacing(1);
            vbox.setPadding(new Insets(10, 10, 0, 10));
            vbox.setAlignment(Pos.TOP_LEFT);

            // Charger les utilisateurs
            if(typeChoix) {
                users = ServerConstante.SERVER.getAllUserNotInServer();
                for (Utilisateur user : users) {
                    JFXCheckBox checkBox = new JFXCheckBox(user.getPrenom() + " " + user.getNom());
                    vbox.getChildren().add(checkBox);
                }
            }else{
                users = ServerConstante.SERVER.getAllUserInServer();
                for (Utilisateur user : users) {
                    JFXToggleButton toggleButton = new JFXToggleButton();
                    toggleButton.setText(user.getPrenom() + " " + user.getNom());
                    toggleButton.setSelected(true);
                    vbox.getChildren().add(toggleButton);
                }
            }

            VBox center = new VBox();
            vbox.getChildren().add(center);
            center.setSpacing(20);
            center.setPadding(new Insets(10, 10, 10, 10));
            center.setAlignment(Pos.CENTER);

            // Bouton d'ajout
            String actionLabel = null;
            if(typeChoix){
                actionLabel = "+";
            }else{
                actionLabel = "--";
            }

            JFXButton add = new JFXButton(actionLabel);
            add.setButtonType(JFXButton.ButtonType.RAISED);
            add.getStyleClass().add("addserverbutton");
            add.setPrefSize(80, 80);
            center.getChildren().add(add);

            add.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    if(typeChoix) {
                        addusers();
                    }else{
                        delusers();
                    }
                }
            });

            // Bouton pour fermer la popup
            JFXButton annuler = new JFXButton("Annuler");
            annuler.setButtonType(JFXButton.ButtonType.FLAT);
            annuler.setPrefSize(200, 50);
            center.getChildren().add(annuler);

            annuler.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Stage stage = (Stage) annuler.getScene().getWindow();
                    stage.close();
                }
            });
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Ajoute les utilisateurs cochés dans le serveur
     */
    private void addusers() {
        try{
        for (Node checkBox : vbox.getChildren()) {
            if (checkBox instanceof JFXCheckBox) {
                if (((JFXCheckBox) checkBox).isSelected()) {
                    String userToAdd = ((JFXCheckBox) checkBox).getText();
                    for (Utilisateur user : users) {
                        String identite = user.getPrenom() + " " + user.getNom();
                        if (identite.equals(userToAdd)) {
                            ServerConstante.SERVER.linkUserToServer(user);
                            continue;
                        }
                    }
                }
            }
        }

        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    /**
     * Ajoute les utilisateurs cochés dans le serveur
     */
    private void delusers() {
        try{
            for (Node toggleButton : vbox.getChildren()) {
                if (toggleButton instanceof JFXToggleButton) {
                    if (!((JFXToggleButton) toggleButton).isSelected()) {
                        String userToAdd = ((JFXToggleButton) toggleButton).getText();
                        for (Utilisateur user : users) {
                            String identite = user.getPrenom() + " " + user.getNom();
                            if (identite.equals(userToAdd)) {
                                //ServerConstante.SERVER.linkUserToServer(user);
                                // Méthode pour retirer du serveur
                                System.out.println("je retire");
                                System.out.println(userToAdd);
                                continue;
                            }
                        }
                    }
                }
            }

            Stage stage = (Stage) vbox.getScene().getWindow();
            stage.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
