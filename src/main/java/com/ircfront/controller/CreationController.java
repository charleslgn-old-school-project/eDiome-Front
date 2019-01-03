package com.ircfront.controller;

import com.ircfront.Utils.HashPassword;
import com.ircfront.Utils.IRCUtils;
import com.ircserv.metier.Constante;
import com.ircserv.metier.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreationController implements Initializable {
  @FXML
  private TextField nom;
  @FXML
  private PasswordField pasword1;
  @FXML
  private PasswordField pasword2;
  @FXML
  private Label lblEror;
  @FXML
  private StackPane panParent;
  @FXML
  private TextField prenom;
  @FXML
  private TextField identifiant;
  @FXML
  private TextField mail_pro;
  @FXML
  private TextField tel_pro;
  @FXML
  private TextField date_naiss;




  @Override
  public void initialize(URL location, ResourceBundle resources) {
    lblEror.setVisible(false);
  }

  public void validate(){
    try {
      String psw1 = pasword1.getText();
      String psw2 = pasword2.getText();
      if (psw1.equals(psw2)){
          Utilisateur user = new Utilisateur();
        int id = Constante.menu.createUser(nom.getText(),HashPassword.hash(psw1));
        IRCUtils.load(new FXMLLoader(getClass().getResource("../../../gui/dashboard.fxml")), id);
      } else {
        lblEror.setVisible(true);
      }
    } catch (Exception e){
      lblEror.setVisible(true);
    }
  }

  public void cancel(){
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/Connection.fxml"));
    IRCUtils.load(loader);
    ((Stage) panParent.getScene().getWindow()).close();
  }



}
