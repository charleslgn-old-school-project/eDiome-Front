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
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.Naming;
import java.util.List;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {

    @FXML
    private VBox vbox;
    private VBox vboxprinc;
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
            vbox.setPadding(new Insets(10, 10, 10, 10));
            vbox.setSpacing(10);
            //vbox.setAlignment(Pos.BASELINE_CENTER);
            vbox.setPrefSize(300,400);

            vboxprinc = new VBox();
            vboxprinc.setPadding(new Insets(10, 10, 0, 10));
            vboxprinc.setAlignment(Pos.TOP_LEFT);

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.getStyleClass().add("menu-bar-2");
            scrollPane.setFitToWidth(true);

            VBox vboxBottom = new VBox();
            vboxBottom.setSpacing(10);
            vboxBottom.setAlignment(Pos.CENTER);

            // Charger les utilisateurs
            if(typeChoix) {
                users = ServerConstante.SERVER.getAllUserNotInServer();
                for (Utilisateur user : users) {
                    vboxprinc.setSpacing(50);
                    JFXCheckBox checkBox = new JFXCheckBox(user.getPrenom() + " " + user.getNom());
                    vboxprinc.getChildren().add(checkBox);
                }
            }else{
                users = ServerConstante.SERVER.getAllUserInServer();
                for (Utilisateur user : users) {
                    vboxprinc.setSpacing(1);
                    JFXToggleButton toggleButton = new JFXToggleButton();
                    toggleButton.setText(user.getPrenom() + " " + user.getNom());
                    toggleButton.setSelected(true);
                    vboxprinc.getChildren().add(toggleButton);
                }
            }

            scrollPane.setContent(vboxprinc);
            //vbox.getChildren().add(scrollPane);



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
            add.setPrefSize(50, 50);
            add.setMinSize(50,50);
            vboxBottom.getChildren().add(add);


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
            annuler.setMinSize(200, 50);
            vboxBottom.getChildren().add(annuler);

            annuler.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Stage stage = (Stage) annuler.getScene().getWindow();
                    stage.close();
                }
            });
            GridPane gridPane = new GridPane();
            gridPane.setMinWidth(400);
            gridPane.add(scrollPane, 0,0);
            gridPane.add(vboxBottom, 0,1);

            for (int i = 0; i < 2; i++) {
                RowConstraints rowConst = new RowConstraints();
                rowConst.setPercentHeight(100.0 / 2);
                gridPane.getRowConstraints().add(rowConst);
            }

            gridPane.getRowConstraints().get(0).setPercentHeight(70);
            gridPane.getRowConstraints().get(1).setPercentHeight(30);
            gridPane.getColumnConstraints().add( new ColumnConstraints(280));
            vbox.getChildren().add(gridPane);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Ajoute les utilisateurs cochés au serveur
     */
    private void addusers() {
        try{
        for (Node checkBox : vboxprinc.getChildren()) {
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

        Alert succes = new Alert(Alert.AlertType.INFORMATION);
        succes.setTitle("Ajout d'un utilisateur");
        succes.setHeaderText("Ajout dans le serveur");
        succes.setContentText("L'utilisateur a été ajouté avec succès.");
        succes.show();

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
