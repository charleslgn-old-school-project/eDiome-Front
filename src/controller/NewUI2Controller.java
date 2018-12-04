package controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import resource.lang.Lang;
import resource.lang.Translate;
import resource.lang.langage.DE;
import resource.lang.langage.EN;
import resource.lang.langage.FR;
import resource.lang.langage.RU;
import resource.lang.typetrad.LabelName;
import resource.lang.typetrad.MenuName;
import resource.lang.typetrad.TitleName;
import start.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class NewUI2Controller implements Initializable {
    private static double xOffset = 0;
    private static double yOffset = 0;
    private final String username = System.getProperty("user.name");
    private int titre = -1;

        @FXML
        private VBox pnPrincipal;

        @FXML
        private Pane pnZoneTravail;

        public Pane getPnZone(){
            Pane pan = this.pnZoneTravail;
            return pan;
        }

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
        private Label lbTitre;


        @FXML
        private JFXHamburger hamburger;

        @FXML
        private JFXDrawer drawer;

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

                String lan = System.getProperty("user.language");
                if(lan.equalsIgnoreCase("fr")){
                    Main.setLangue(new FR());
                } else if (lan.equalsIgnoreCase("de")){
                    Main.setLangue(new DE());
                } else if (lan.equalsIgnoreCase("ru")) {
                    Main.setLangue(new RU());
                } else {
                    Main.setLangue(new EN());
                }

                new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        translate();
                    }
                }.start();

                mnuBar.setOnMousePressed(this::mousePressed);
                mnuBar.setOnMouseDragged(this::mouseDrag);
                mnuBar.setOnMouseReleased(this::mouseRealease);
            }catch (Exception ex){
               System.out.println(ex);
            }
        }

    /**
     * permet de déplacer le fenettre
     * @param event le click de la souris
     */
    private void mouseDrag(MouseEvent event){
        Main.getPrimaryStage().setX(event.getScreenX() - xOffset);
        Main.getPrimaryStage().setY(event.getScreenY() - yOffset);
    }

    /**
     * permet de replacer la fenettre si elle est mise en haut
     * @param event le click de la souris
     */
    private void mouseRealease(MouseEvent event){
        if(event.getSceneY() == 0){
            Main.getPrimaryStage().setY(0);
        }else if(Main.getPrimaryStage().getY() < 0){
            Main.getPrimaryStage().setY(0);
        }
    }

    /**
     * permet de déplacer le fenettre
     * @param event le relachemleltn de la souris
     */
    private void mousePressed(MouseEvent event){
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    /**
     * ferme l'application
     */
    public void close(){
        ((Stage)pnPrincipal.getScene().getWindow()).close();
    }

    /**
     * Traduction des composants
     */
    private void translate() {
        Lang lang = Main.getLangue();

        if (titre != -1) {
            lbTitre.setText(Translate.haveIt(titre, lang.titleName));
        }

        //Main.getPrimaryStage().setTitle(Translate.haveIt(LabelName.TITLE, lang.label));
        //AffichageIRC.setText(Translate.haveIt(TitleName.LANGUAGE_TO_MORSE, lang.titleName));
        //btFrToLeet.setText(Translate.haveIt(TitleName.LANGUAGE_TO_L33T, lang.titleName));
        //btMorseToFr.setText(Translate.haveIt(TitleName.MORSE_TO_LANGUAGE, lang.titleName));
        //btTradDirecte.setText(Translate.haveIt(TitleName.DIRECT_TRANSLATE, lang.titleName));
        //lbBienvenu.setText(Translate.haveIt(LabelName.WELCOME, lang.label) + " " + username);
        //lblTranslate.setText(Translate.haveIt(LabelName.TITLE, lang.label));
        mnuMenu.setText(Translate.haveIt(MenuName.MENU_MENU, lang.menu));
        mnuAbout.setText(Translate.haveIt(MenuName.MENU_ABOUT, lang.menu));
        mnuHelp.setText(Translate.haveIt(MenuName.MENU_HELP, lang.menu));
        mnuLanguage.setText(Translate.haveIt(MenuName.MENU_LANGUAGE, lang.menu));
    }

    /**
     * Affecte la langue Française
     */
    public void toFr() {
        Main.setLangue(new FR());
    }

    /**
     * Affecte la langue Allemande
     */
    public void toDe() {
        Main.setLangue(new DE());
    }

    /**
     * Affecte la langue Russe
     */
    public void toRu() {
        Main.setLangue(new RU());
    }

    /**
     * Affecte la langue Englaise
     */
    public void toEn() {
        Main.setLangue(new EN());
    }

    /**
     * Lance la fenêtre à propos de l'application
     */
    public void about() {
        try {
            Stage st = new Stage();
            st.initModality(Modality.NONE);
            st.initOwner(pnPrincipal.getScene().getWindow());
            st.initStyle(StageStyle.UNDECORATED);

            Parent root = FXMLLoader.load(getClass().getResource("../gui/propos.fxml"));
            Scene scene = new Scene(root, 500, 300);
            st.setScene(scene);
            st.setResizable(false);
            st.show();
            st.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
                if (!newPropertyValue){
                    st.close();
                }
            });

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void toBlack(){
        System.out.println("hcjdhdj");
        Main.getPrimaryStage().getScene().getStylesheets().clear();
        Main.getPrimaryStage().getScene().getStylesheets().add(getClass().getResource("..//gui/css/main-black.css").toExternalForm());
    }
    public void toWhite(){
        Main.getPrimaryStage().getScene().getStylesheets().clear();
        Main.getPrimaryStage().getScene().getStylesheets().add(getClass().getResource("..//gui/css/main-white.css").toExternalForm());
    }
}







