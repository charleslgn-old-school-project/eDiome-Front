package com.ircfront.controller;

import com.ircfront.utils.XMLDataFinder;
import com.ircfront.start.Main;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import com.ircfront.utils.lang.Lang;
import com.ircfront.utils.lang.Translate;
import com.ircfront.utils.lang.typetrad.DisclimerName;

import java.net.URL;
import java.util.ResourceBundle;

public class ProposController implements Initializable{
    @FXML
    private GridPane panClasse;

    @FXML
    private Label lblBuild;
    @FXML
    private Label lblRealise;
    @FXML
    private Label lblCadre;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblNoBuild, lblVersion;

   /**
    * Initialisation de la fenêtre, lance la traduction en fonction de la langue sélectionnée
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      BackgroundImage myBI;
      try{
        myBI= new BackgroundImage(new Image("image/a-propo.png",640,400,true,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 25, true, Side.TOP, -25, false),
                BackgroundSize.DEFAULT);
        panClasse.setBackground(new Background(myBI));
      } catch (Exception e){
        e.printStackTrace();
      }
      lblNoBuild.setText(" "+ XMLDataFinder.getBuildNum()+" ");
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                translate();
            }
        }.start();
    }

  /**
   * Traduction des composants
   */
    private void translate() {
        Lang lang = Main.getLangue();

        lblBuild.setText(Translate.haveIt(DisclimerName.LBL_BUILD, lang.disclimer));
        lblCadre.setText(Translate.haveIt(DisclimerName.LBL_CADRE, lang.disclimer));
        lblRealise.setText(Translate.haveIt(DisclimerName.LBL_REALISE, lang.disclimer));
        lblVersion.setText(Translate.haveIt(DisclimerName.LBL_VERSION, lang.disclimer) + " " + XMLDataFinder.getVersion());
    }
}