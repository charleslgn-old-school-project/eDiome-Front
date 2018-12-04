package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import resource.lang.typetrad.TitleName;

public class SandwitchController {

    @FXML
    private Label lblTranslate;

    @FXML
    private JFXButton AffichageIRC;

    @FXML
    void AffichageIRCClick(MouseEvent event) {
        //this.lbTitre.setText("Français vers Morse");
        fadeout(NewUI2Controller.getPnZone( ));
        fadeout(NewUI2Controller.);
        //titre = TitleName.LANGUAGE_TO_MORSE;
        loadFxml("..//gui/IRC.fxml");
    }

    /**
     * Lance la fenêtre correspondante dans la pane prévue à cet effet
     * @param form : fxml à charger
     */
    private void loadFxml(String form) {
        try {
            Pane pan = NewUI2Controller.getPanePrincipal();

            pan.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource(form));
            NewUI2Controller.getPanePrincipal().getChildren().add(newLoadedPane);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void initialize() {}

    /**
     * Effet de transition
     * @param pane : Pane (zone de travail) où s'affichent les différentes fonctionnalités
     */
    private void fadeout(Pane pane) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(250), pane);
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }
}
