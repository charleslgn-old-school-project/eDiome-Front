package com.ircfront.controller;

import com.ircfront.utils.XMLDataFinder;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionDialogController implements Initializable {
    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private JFXButton answerYes;

    @FXML
    private JFXButton answerNo;

    @FXML
    private VBox vbox;

    private boolean data;

    private String titletodisplay;
    private String messagetodisplay;

    public OptionDialogController(String message, String title){
        this.titletodisplay = title;
        this.messagetodisplay = message;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String color = XMLDataFinder.getTheme();
        vbox.getStylesheets().add(getClass().getResource("/gui/css/main-" + color + ".css").toExternalForm());
        vbox.getStyleClass().add("menu-bar-2");

        this.title.setText(this.titletodisplay);
        this.message.setText(this.messagetodisplay);
    }

    @FXML
    void no(ActionEvent event) {
        data = false;
        ((Stage) title.getScene().getWindow()).close();
    }

    @FXML
    void yes(ActionEvent event) {
        data = true;
        ((Stage) title.getScene().getWindow()).close();
    }

    boolean getData() {
        return data;
    }
}
