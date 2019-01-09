package com.ircfront.controller;

import com.ircfront.Utils.ControllerUtils;
import com.ircfront.utils.HashPassword;
import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.ircserv.metier.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
  private GridPane panParent;
  @FXML
  private TextField prenom;
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
          user.setNom(nom.getText());
          user.setPrenom(prenom.getText());
          user.setMot_de_passe(HashPassword.hash(pasword1.getText()));
          user.setTelephone_pro(tel_pro.getText());
          user.setMail_pro(mail_pro.getText());
          user.setIdentifiant(nom.getText()+"_"+prenom.getText());
        int id = ServerConstante.MENU.createUser(user);
        XMLDataFinder.setPseudo((nom.getText()+"_"+prenom.getText()));
        XMLDataFinder.setPassword(HashPassword.hash(pasword1.getText()));


        ControllerUtils.load(new FXMLLoader(getClass().getResource("../../../gui/dashboard.fxml")), id);
        ((Stage) panParent.getScene().getWindow()).close();

      } else {
        lblEror.setVisible(true);
      }
    } catch (Exception e){
      lblEror.setVisible(true);
      e.printStackTrace();
    }
  }

  public void cancel(){
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/Connection.fxml"));
    ControllerUtils.load(loader);
    ((Stage) panParent.getScene().getWindow()).close();
  }
}