package com.ircfront.controller;

import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.ircserv.metier.Server;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AdduserController implements Initializable {

    @FXML
    private VBox vbox;

    private int nbServ;

    public AdduserController(int nbServ){
        this.nbServ = nbServ;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Style
        String color = XMLDataFinder.getTheme();
        vbox.getStylesheets().add(getClass().getResource("../../../gui/css/main-" + color + ".css").toExternalForm());
        System.out.println(this.nbServ);

        // Charger les utilisateurs
        List<String> users = Arrays.asList("cyril", "sup2", "sup3");
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(10, 10, 0, 10));
        vbox.setAlignment(Pos.TOP_CENTER);

        // Une Checkbox pour chaque utilisateur
        for (String user : users) {
            JFXCheckBox checkBox = new JFXCheckBox(user);
            vbox.getChildren().add(checkBox);
        }

        // Bouton d'ajout
        JFXButton add = new JFXButton("+");
        add.setButtonType(JFXButton.ButtonType.RAISED);
        add.getStyleClass().add("addserverbutton");
        add.setPrefSize(80, 80);
        vbox.getChildren().add(add);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                addusers();
            }
        });

        // Bouton pour fermer la popup
        JFXButton annuler = new JFXButton("Annuler");
        annuler.setButtonType(JFXButton.ButtonType.FLAT);
        annuler.setPrefSize(200, 50);
        vbox.getChildren().add(annuler);

        annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage) annuler.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * Ajoute les utilisateurs cochés dans le serveur
     */
    private void addusers(){
        for (Node checkBox : vbox.getChildren()) {
            if(checkBox instanceof JFXCheckBox){
                if(((JFXCheckBox) checkBox).isSelected()) {
                    String userToAdd = ((JFXCheckBox) checkBox).getText();
                    System.out.println(userToAdd);
                    // Appeler la méthode d'ajout dans la bdd
                }
            }
        }

        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
    }
}
