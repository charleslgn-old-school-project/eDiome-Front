package controller;

import Utils.ResizeHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
import resource.lang.typetrad.MenuName;
import start.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class NewUI2Controller implements Initializable {
    private static double xOffset = 0;
    private static double yOffset = 0;

    @FXML
        private VBox pnPrincipal;

        @FXML
        private BorderPane pnZoneTravail;

        @FXML
        private MenuBar mnuBar;

        @FXML
        private Menu mnuMenu;

        @FXML
        private Menu mnuLanguage;

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
                        this.pnZoneTravail.setPadding(new Insets(0,0,0,0));
                    } else {
                        drawer.toggle();
                        this.pnZoneTravail.setPadding(new Insets(0,0,0,260));
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

                addServ();

                BorderPane.setAlignment(this.pnZoneTravail, Pos.CENTER);
                mnuBar.setOnMousePressed(this::mousePressed);
                mnuBar.setOnMouseDragged(this::mouseDrag);
                mnuBar.setOnMouseReleased(this::mouseRealease);
                mnuBar.setOnMouseClicked(this::mouseClicked);

            }catch (Exception ex){
               System.out.println(ex);
            }
        }

    public void AffichageIRCClick() {
        //this.lbTitre.setText("Français vers Morse");
        fadeout(pnZoneTravail);
        fadeout(pnZoneTravail);
        //titre = TitleName.LANGUAGE_TO_MORSE;
        loadFxml("..//gui/IRC.fxml");

    }

    /**
     * Lance la fenêtre correspondante dans la pane prévue à cet effet
     * @param form : fxml à charger
     */
    private void loadFxml(String form) {
        try {
            Pane pan = pnZoneTravail;

            pan.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource(form));
            //newLoadedPane.setPrefWidth(pnZoneTravail.getPrefWidth());
            //newLoadedPane.setMinWidth(pnZoneTravail.getPrefWidth());
            //newLoadedPane.setMaxWidth(pnZoneTravail.getPrefHeight());
            //newLoadedPane.setPrefHeight(pnZoneTravail.getPrefHeight());
            pnZoneTravail.setCenter(newLoadedPane);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

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

    /**
     * permet de déplacer le fenettre
     * @param event le click de la souris
     */
    private void mouseDrag(MouseEvent event){
        Main.getPrimaryStage().setMaximized(false);
        setOpacity(0.8);

        Main.getPrimaryStage().setX(event.getScreenX() - xOffset);
        Main.getPrimaryStage().setY(event.getScreenY() - yOffset);
    }

    private void setOpacity(double opacity){
        pnPrincipal.getScene().getWindow().setOpacity(opacity);
    }

    /**
     * permet de replacer la fenettre si elle est mise en haut
     * @param event le click de la souris
     */
    private void mouseRealease(MouseEvent event){
        if(event.getSceneY() == 0){
            //Main.getPrimaryStage().setY(0);
            Main.getPrimaryStage().setMaximized(true);
        }else if(Main.getPrimaryStage().getY() < 0){
            Main.getPrimaryStage().setY(0);
        }
        setOpacity(1);


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
     * permet de déplacer le fenettre
     * @param event le relachemleltn de la souris
     */
    private void mouseClicked(MouseEvent event){
        if(event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                Main.getPrimaryStage().setMaximized(!Main.getPrimaryStage().isMaximized());
            }
        }
    }

    /**
     * ferme l'application
     */
    public void close(){
        ((Stage)pnPrincipal.getScene().getWindow()).close();
    }

    public void Maximize(){
        if(((Stage)pnPrincipal.getScene().getWindow()).isMaximized()) {
            ((Stage) pnPrincipal.getScene().getWindow()).setMaximized(false);
        }else{
            ((Stage) pnPrincipal.getScene().getWindow()).setMaximized(true);
        }
    }

    public void Shrink() {
        ((Stage) pnPrincipal.getScene().getWindow()).setIconified(true);
    }
    /**
     * Traduction des composants
     */
    private void translate() {
        Lang lang = Main.getLangue();

        int titre = -1;
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

    private void addServ(){
            VBox vBox = new VBox();
            //vBox.setAlignment(Pos.TOP_CENTER);
            vBox.getStyleClass().add("menu-bar-2");
            vBox.setSpacing(20);
            vBox.setPadding(new Insets(10, 10, 0, 10));
            ImageView iw = new ImageView();
            Image logo = new Image("resource/Images/logov4.png");
            iw.setImage(logo);
            iw.setFitHeight(34.5);
            iw.setFitWidth(150);
            vBox.getChildren().add(iw);

            JFXButton jfxButton = new JFXButton("server1");
            jfxButton.setPrefSize(Double.MAX_VALUE, 60);
            jfxButton.setOnAction(event -> AffichageIRCClick());

            JFXButton jfxButton2 = new JFXButton("server2");
            jfxButton2.setPrefSize(Double.MAX_VALUE, 60);
            jfxButton2.setOnAction(event -> AffichageIRCClick());

            JFXButton jfxButton3 = new JFXButton("server3");
            jfxButton3.setPrefSize(Double.MAX_VALUE, 60);
            jfxButton3.setOnAction(event -> AffichageIRCClick());

            JFXButton jfxButton4 = new JFXButton("server4");
            jfxButton4.setPrefSize(Double.MAX_VALUE, 60);
            jfxButton4.setOnAction(event -> AffichageIRCClick());

            vBox.getChildren().add(jfxButton);
            vBox.getChildren().add(jfxButton2);
            vBox.getChildren().add(jfxButton3);
            vBox.getChildren().add(jfxButton4);

            drawer.setSidePane(vBox);
    }
}







