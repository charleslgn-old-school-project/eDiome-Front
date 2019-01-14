package com.ircfront.controller;

import com.ircfront.utils.ControllerUtils;
import com.ircfront.utils.HashPassword;
import com.ircfront.utils.MoveUtils;
import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.ircserv.metier.Utilisateur;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreationController implements Initializable {

  @FXML
  private JFXTextField nom;
  @FXML
  private JFXPasswordField pasword1;
  @FXML
  private JFXPasswordField pasword2;
  @FXML
  private Label lblEror;
  @FXML
  private GridPane panParent;
  @FXML
  private JFXTextField prenom;
  @FXML
  private JFXTextField mail_pro;
  @FXML
  private JFXTextField tel_pro;
  @FXML
  private JFXTextField date_naiss;
  @FXML
  private ProgressBar passwordStrongValidator;

  //validity
  private boolean telValidity;
  private boolean melValidity;
  private boolean dateValidity;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    lblEror.setVisible(false);
    MoveUtils.moveEvent(panParent);
  }

  public void validate(){
    try {
      String psw1 = pasword1.getText();
      String psw2 = pasword2.getText();
      if (psw1.equals(psw2) && telValidity && melValidity){
          Utilisateur user = new Utilisateur();
          user.setNom(nom.getText());
          user.setPrenom(prenom.getText());
          user.setPassword(HashPassword.hash(pasword1.getText()));
          user.setTelephonePro(tel_pro.getText());
          user.setMailPro(mail_pro.getText());
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

  @FXML
  public void validateEmail(){
    melValidity = mail_pro.getText().matches("^[a-z0-9]+([\\-.][a-z0-9]+)*@[a-z0-9]+([\\-.][a-z0-9]+)*\\.[a-z]{2,5}$");
    mail_pro.setEffect(new DropShadow(10, melValidity ? Color.GREEN : Color.RED));
  }

  @FXML
  public void validateTelephone(){
    String tel = tel_pro.getText();
    tel = tel.replaceAll("(\\.| )", "");
    tel = tel.replace("+33" , "0");
    telValidity = tel.matches("0[1-9][0-9]{8}");
    tel_pro.setEffect(new DropShadow(10, telValidity ? Color.GREEN : Color.RED));
    System.out.println(tel);
  }
}