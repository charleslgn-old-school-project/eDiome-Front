package com.ircfront.controller;
import com.ircfront.start.Main;
import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
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
        // Ici la création du serveur

        // à la fermeture de la fenêtre, recréer le menu de server (pour afficher le serveur nouvellement créé)
    }

 /*   @Override
    public int createNewServer() throws RemoteException {
        try {
            int port = ServerConstante.PORT;
            LocateRegistry.getRegistry(port);

            Naming.rebind("//" + ServerConstante.IP + ":" + port + "/serv" + numServ, new ServerImpl(port, numServ));
            return numServ++;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("echec : " + e);
        }
        return -1;
    }
*/

}
