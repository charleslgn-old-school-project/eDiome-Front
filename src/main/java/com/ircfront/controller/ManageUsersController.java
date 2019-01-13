package com.ircfront.controller;

import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.ircserv.inter.ServerInterface;
import com.ircserv.metier.Droit;
import com.ircserv.metier.Utilisateur;
import com.ircserv.metier.UtilisateurDroitServer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {

    @FXML
    private VBox vbox;
    private VBox vboxprinc;
    private int nbServ;
    private int droit;
    private int typeChoix;

    public ManageUsersController(int nbServ, int droit, int typeChoix) {
        this.nbServ = nbServ;
        this.droit = droit;
        this.typeChoix = typeChoix;
    }

    private List<Utilisateur> users;
    private List<UtilisateurDroitServer> utilisateurDroitServers;

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
            vbox.setPrefSize(300, 400);

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
            utilisateurDroitServers = ServerConstante.SERVER.getAllDroit();
            List<Droit> droits = ServerConstante.MENU.getDroit();

            switch (typeChoix) {
                case 0:
                    users = ServerConstante.SERVER.getAllUserNotInServer();
                    for (Utilisateur user : users) {
                        vboxprinc.setSpacing(50);
                        JFXCheckBox checkBox = new JFXCheckBox(user.getPrenom() + " " + user.getNom());
                        vboxprinc.getChildren().add(checkBox);
                    }
                    break;
                case 1:
                    users = ServerConstante.SERVER.getAllUserInServer();
                    for (Utilisateur user : users) {
                        vboxprinc.setSpacing(1);
                        JFXToggleButton toggleButton = new JFXToggleButton();
                        toggleButton.setText(user.getPrenom() + " " + user.getNom());
                        toggleButton.setSelected(true);
                        vboxprinc.getChildren().add(toggleButton);
                    }
                    break;
                case 2:
                    droits.remove(4);
                    for (int i = 0; i <= droit; i++) {
                        droits.remove(0);
                    }
                    for (UtilisateurDroitServer uds : utilisateurDroitServers) {
                        if (uds.getDroit().getId() > droit) {
                            String identite = uds.getUser().getPrenom() + " " + uds.getUser().getNom();
                            JFXComboBox jfxComboBox = new JFXComboBox();
                            jfxComboBox.setItems(FXCollections.observableArrayList(droits));
                            jfxComboBox.getSelectionModel().select(uds.getDroit().getId() - 1);
                            jfxComboBox.setUserData(uds.getUser());
                            HBox hbox = new HBox(jfxComboBox, new Label(identite));
                            hbox.setSpacing(20);
                            hbox.setAlignment(Pos.TOP_LEFT);
                            vboxprinc.getChildren().add(hbox);
                        }
                    }
                    break;
                case 3:
                    for (UtilisateurDroitServer uds : utilisateurDroitServers) {
                        String identite = uds.getUser().getPrenom() + " " + uds.getUser().getNom();
                        vboxprinc.getChildren().add(new Label(identite));
                    }
                    break;
            }

            scrollPane.setContent(vboxprinc);
            //vbox.getChildren().add(scrollPane);


            // Bouton d'ajout
            String actionLabel = null;
            if (typeChoix == 0) {
                actionLabel = "+";
            } else if (typeChoix == 1) {
                actionLabel = "--";
            } else if (typeChoix == 2) {
                actionLabel = "Confirmer";
            }

            JFXButton add = new JFXButton(actionLabel);
            add.setButtonType(JFXButton.ButtonType.RAISED);
            add.getStyleClass().add("addserverbutton");
            add.setPrefSize(typeChoix == 2 ? 200 : 50, 50);
            add.setMinSize(typeChoix == 2 ? 200 : 50, 50);
            vboxBottom.getChildren().add(add);

            add.setVisible(typeChoix != 3);

            add.setOnAction(e -> {
                if (typeChoix == 0) {
                    addusers();
                } else if (typeChoix == 1) {
                    delusers();
                } else if (typeChoix == 2) {
                    setUsersDroit();
                }
            });

            // Bouton pour fermer la popup
            JFXButton annuler = new JFXButton(typeChoix != 3 ? "Annuler" : "Retour");
            annuler.setButtonType(JFXButton.ButtonType.FLAT);
            annuler.setPrefSize(200, 50);
            annuler.setMinSize(200, 50);
            annuler.getStyleClass().add("addserverbutton");
            vboxBottom.getChildren().add(annuler);


            annuler.setOnAction(e -> close());

            GridPane gridPane = new GridPane();
            gridPane.setMinWidth(400);
            gridPane.add(scrollPane, 0, 0);
            gridPane.add(vboxBottom, 0, 1);

            for (int i = 0; i < 2; i++) {
                RowConstraints rowConst = new RowConstraints();
                rowConst.setPercentHeight(100.0 / 2);
                gridPane.getRowConstraints().add(rowConst);
            }

            gridPane.getRowConstraints().get(0).setPercentHeight(70);
            gridPane.getRowConstraints().get(1).setPercentHeight(30);
            gridPane.getColumnConstraints().add(new ColumnConstraints(280));
            vbox.getChildren().add(gridPane);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Ajoute les utilisateurs cochés au serveur
     */
    private void addusers() {
        try {
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

            close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Ajoute les utilisateurs cochés dans le serveur
     */
    private void delusers() {
        try {
            for (Node toggleButton : vboxprinc.getChildren()) {
                if (toggleButton instanceof JFXToggleButton && !((JFXToggleButton) toggleButton).isSelected()) {
                    String userToAdd = ((JFXToggleButton) toggleButton).getText();
                    for (Utilisateur user : users) {
                        String identite = user.getPrenom() + " " + user.getNom();
                        if (identite.equals(userToAdd)) {
                            ServerConstante.SERVER.unlinkUserToServer(user);
                            continue;
                        }
                    }
                }
            }
            close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void close() {
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
    }

    private void setUsersDroit() {
        try {
            for (Node box : vboxprinc.getChildren()) {
                HBox hBox = (HBox) box;
                JFXComboBox jfxComboBox = (JFXComboBox) hBox.getChildren().get(0);
                Utilisateur user = (Utilisateur) jfxComboBox.getUserData();
                Droit droit = (Droit) jfxComboBox.getSelectionModel().getSelectedItem();
                ServerConstante.SERVER.setDroit(user, droit);
            }
            close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
