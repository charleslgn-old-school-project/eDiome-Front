package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SandwitchController {

    @FXML
    private Label lblTranslate;

    @FXML
    private JFXButton AffichageIRC;

    @FXML
    void AffichageIRCClick(MouseEvent event) {
        System.out.println("chjh");
    }

    public void initialize() {}
}
