package controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NewUI2Controller implements Initializable {

        @FXML
        private MenuBar mnuBar;

        @FXML
        private Menu mnuMenu;

        @FXML
        private Menu mnuLanguage;

        @FXML
        private MenuItem mnuFr;

        @FXML
        private MenuItem mnuEn;

        @FXML
        private MenuItem mnuDe;

        @FXML
        private MenuItem mnuRu;

        @FXML
        private Menu mnuHelp;

        @FXML
        private MenuItem mnuAbout;

        @FXML
        private Button btnClose;

        @FXML
        private Label lblTranslate;

        @FXML
        private JFXHamburger hamburger;

        @FXML
        private JFXDrawer drawer;

        @FXML
        void about(ActionEvent event) {

        }

        @FXML
        void close(ActionEvent event) {

        }

        @FXML
        void toBlack(ActionEvent event) {

        }

        @FXML
        void toDe(ActionEvent event) {

        }

        @FXML
        void toEn(ActionEvent event) {

        }

        @FXML
        void toFr(ActionEvent event) {

        }

        @FXML
        void toRu(ActionEvent event) {

        }

        @FXML
        void toWhite(ActionEvent event) {

        }

        @Override
        public void initialize(URL url, ResourceBundle rb){
            try {
                VBox box = FXMLLoader.load(getClass().getResource("../gui/Sandwitch.fxml"));
                drawer.setSidePane(box);
                HamburgerBackArrowBasicTransition burgertask = new HamburgerBackArrowBasicTransition(hamburger);
                burgertask.setRate(-1);
                hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                    burgertask.setRate(burgertask.getRate() * -1);
                    burgertask.play();

                    if (drawer.isOpened()) {
                        drawer.close();
                    } else {
                        drawer.toggle();
                    }
                });
            }catch (Exception ex){
                System.out.println(ex);
            }
        }

    }







