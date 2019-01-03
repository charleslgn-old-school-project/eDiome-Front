package com.ircfront.controller;

import com.ircfront.Utils.HashPassword;
import com.ircfront.Utils.IRCUtils;
import com.ircfront.Utils.XMLDataFinder;
import com.ircfront.start.Main;
import com.ircserv.metier.Constante;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {
  private static double xOffset = 0;
  private static double yOffset = 0;

  @FXML
  private JFXTextField name;
  @FXML
  private JFXPasswordField psw;
  @FXML
  private Label lblEror;
  @FXML
  private StackPane panParent;
  @FXML
  private GridPane movablePan;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    lblEror.setVisible(false);
    autoConnect();
    lblEror.setVisible(false);
    name.setText(XMLDataFinder.getPseudo());
    psw.setText("");
    movablePan.setOnMousePressed(this::mousePressed);
    movablePan.setOnMouseDragged(this::mouseDrag);
    movablePan.setOnMouseReleased(this::mouseRelease);
  }

  public void createConnection() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/Creation.fxml"));
    IRCUtils.load(loader);
    ((Stage) panParent.getScene().getWindow()).close();
  }

  public void autoConnect() {
    try {
      String user = XMLDataFinder.getPseudo();
      String pass = XMLDataFinder.getPassword();

      int res = Constante.menu.connect(user, pass);
      if (res != -1) {
        Platform.runLater(() -> loadIRC(res));
      }
    } catch (Exception e) {
      lblEror.setVisible(true);
    }
  }

  public void connect() {
    try {
      String user = name.getText();
      String pass = psw.getText();

      int res = Constante.menu.connect(user, HashPassword.hash(pass));
      if (res == -1) {
        lblEror.setVisible(true);
      } else {
        XMLDataFinder.setPseudo(user);
        XMLDataFinder.setPassword(HashPassword.hash(pass));
        loadIRC(res);
      }
    } catch (Exception e) {
      lblEror.setVisible(true);
    }
  }

  private void loadIRC(int userId) {
    IRCUtils.load(new FXMLLoader(getClass().getResource("../../../gui/dashboard.fxml")), userId);
    ((Stage) panParent.getScene().getWindow()).close();
  }

  /**
   * call to move the window
   */
  private void mouseDrag(MouseEvent event) {
    Main.getPrimaryStage().setMaximized(false);
    if (Main.getPrimaryStage().getY() != event.getScreenY()) {
      Main.getPrimaryStage().setX(event.getScreenX() - xOffset);
      Main.getPrimaryStage().setY(event.getScreenY() - yOffset);
    }
  }

  /**
   * replace the window if it is out of the screen
   */
  private void mouseRelease(MouseEvent event) {
    if (Main.getPrimaryStage().getY() < 0) {
      Main.getPrimaryStage().setY(0);
    }
  }

  /**
   * move the window
   */
  private void mousePressed(MouseEvent event) {
    xOffset = event.getSceneX();
    yOffset = event.getSceneY();
  }
}
