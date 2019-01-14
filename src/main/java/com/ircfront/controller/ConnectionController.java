package com.ircfront.controller;

import com.ircfront.utils.ControllerUtils;
import com.ircfront.utils.HashPassword;
import com.ircfront.utils.MoveUtils;
import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {

  @FXML
  private JFXTextField name;
  @FXML
  private JFXPasswordField psw;
  @FXML
  private Label lblEror;
  @FXML
  private GridPane panParent;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      lblEror.setVisible(false);
      autoConnect();
      lblEror.setVisible(false);
      name.setText(XMLDataFinder.getPseudo());
      psw.setText("");
      MoveUtils.moveEvent(panParent);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void createConnection() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/Creation.fxml"));
    ControllerUtils.load(loader);
    ((Stage) panParent.getScene().getWindow()).close();
  }

  private void autoConnect() {
    try {
      String user = XMLDataFinder.getPseudo();
      String pass = XMLDataFinder.getPassword();

      int res = ServerConstante.MENU.connect(user, pass);
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

      int res = ServerConstante.MENU.connect(user, HashPassword.hash(pass));
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
    ControllerUtils.load(new FXMLLoader(getClass().getResource("../../../gui/dashboard.fxml")), userId);
    ((Stage) panParent.getScene().getWindow()).close();
  }


}
