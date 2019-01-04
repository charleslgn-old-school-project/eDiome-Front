package com.ircfront.controller;

import com.ircfront.utils.ControllerUtils;
import com.ircfront.utils.constante.ServerConstante;
import com.ircserv.inter.MenuInterface;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ResourceBundle;

public class splashScreenController implements Initializable {

  @FXML
  ProgressIndicator progressIndicator;


  @FXML
  GridPane panParent;

  @FXML
  Label identifiant;

  /**
   * Initialisation de la fenêtre, lance la traduction en fonction de la langue sélectionnée
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    new Thread(new SplashScreen()).start();
  }

  class SplashScreen extends Task {

    @Override
    public Object call() throws InterruptedException {
      Thread.sleep(1000);
      try {
        LocateRegistry.getRegistry(ServerConstante.PORT);
        ServerConstante.MENU = (MenuInterface) Naming.lookup("//" + ServerConstante.IP + ":" + ServerConstante.PORT + "/menu");
        Platform.runLater(() -> {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/Connection.fxml"));
          ControllerUtils.load(loader);
          ((Stage) panParent.getScene().getWindow()).close();
        });
      } catch (NotBoundException | MalformedURLException | RemoteException e) {
        e.printStackTrace();
        identifiant.setText("We can't find any connection");
        progressIndicator.setStyle("-fx-progress-color: red;");
      }
      return null;
    }
  }

}
