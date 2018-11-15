package controller;

import start.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

public class NewUIController {
    private static double xOffset = 0;
    private static double yOffset = 0;

    @FXML
    private Label lbBienvenu;
    private final String username = System.getProperty("user.name");
    private int titre = -1;

    @FXML
    private Label lblTranslate;

    @FXML
    private Label lbTitre;

    @FXML
    private Button AffichageIRC;
    @FXML
    private Button btFrToLeet;
    @FXML
    private Button btMorseToFr;
    @FXML
    private Button btTradDirecte;

    @FXML
    private Pane pnZoneTravail;

    @FXML
    private VBox pnPrincipal;

    @FXML
    private MenuBar mnuBar;
    @FXML
    private Menu mnuMenu;
    @FXML
    private Menu mnuHelp;
    @FXML
    private Menu mnuLanguage;
    @FXML
    private MenuItem mnuAbout;

    @FXML
    private GridPane moveBar;

    /**
     * Initialisation de la fenêtre, lance la traduction en fonction de la langue sélectionnée
     */
    public void initialize() {
        /*if(!MainJavaFx.loadedSplash) {
            loadSplash();
        }*/
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

        moveBar.setOnMousePressed(this::mousePressed);
        moveBar.setOnMouseDragged(this::mouseDrag);
        moveBar.setOnMouseReleased(this::mouseRealease);

        mnuBar.setOnMousePressed(this::mousePressed);
        mnuBar.setOnMouseDragged(this::mouseDrag);
        mnuBar.setOnMouseReleased(this::mouseRealease);
    }

    /**
     * fonction permetant la creation d'un plash screen
     */
//    private void loadSplash(){
//       try{
//            MainJavaFx.loadedSplash = true;
//            StackPane pane = FXMLLoader.load(getClass().getResource("../gui/SplashScreen.fxml"));
//            pnPrincipal.getChildren().setAll(pane);
//
//
//            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), pane);
//            fadeIn.setFromValue(0);
//            fadeIn.setToValue(1);
//            fadeIn.setCycleCount(1);
//            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
//            fadeOut.setFromValue(1);
//            fadeOut.setToValue(0);
//            fadeOut.setCycleCount(1);
//            fadeIn.play();
//            fadeIn.setOnFinished((e) -> fadeOut.play());
//            fadeOut.setOnFinished((e) ->{
//                try {
//                    VBox parent = FXMLLoader.load(getClass().getResource("../gui/NewUI.fxml"));
//                    pnPrincipal.getChildren().setAll(parent);
//                    FadeTransition fadeInparent = new FadeTransition(Duration.seconds(1), parent);
//                    fadeInparent.setFromValue(0);
//                    fadeInparent.setToValue(1);
//                    fadeInparent.setCycleCount(1);
//                    fadeInparent.play();
//                }catch (Exception ex){
//                    System.out.println(ex);
//                }
//            });
//
//        }catch(Exception ex){
//            System.out.println(ex);
//        }
//    }

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
     * Lance la fenêtre : IRC
     */
    public void AffichageIRCClick() {
        this.lbTitre.setText("Français vers Morse");
        fadeout(this.pnZoneTravail);
        titre = TitleName.LANGUAGE_TO_MORSE;
        loadFxml("..//gui/sample.fxml");
    }

    /**
     * Lance la fenêtre : traduction français vers l33t
     */
    public void btFrToLeetClick() {
        this.lbTitre.setText("Français vers L33t");
        fadeout(this.pnZoneTravail);
        //titre = TitleName.LANGUAGE_TO_L33T;
        loadFxml("..//gui/FrToLeet.fxml");
    }

    /**
     * Lance la fenêtre : traduction morse vers français
     */
    public void btMorseToFrClick() {
        this.lbTitre.setText("Morse vers français");
        fadeout(this.pnZoneTravail);
        titre = TitleName.MORSE_TO_LANGUAGE;
        loadFxml("..//gui/AllToFr.fxml");
    }

    /**
     * Lance la fenêtre : traduction directe dans les deux sens (français vers morse / morse vers français)
     */
    public void btTradDirecteClick() {
        this.lbTitre.setText("Traduction directe");
        fadeout(this.pnZoneTravail);
        titre = TitleName.DIRECT_TRANSLATE;
        loadFxml("..//gui/tradDirecte.fxml");
    }

    /**
     * Lance la fenêtre correspondante dans la pane prévue à cet effet
     * @param form : fxml à charger
     */
    private void loadFxml(String form) {
        try {
            pnZoneTravail.getChildren().clear();
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource(form));
            this.pnZoneTravail.getChildren().add(newLoadedPane);
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
     * Traduction des composants
     */
    private void translate() {
        Lang lang = Main.getLangue();

        if (titre != -1) {
            lbTitre.setText(Translate.haveIt(titre, lang.titleName));
        }

        //Main.getPrimaryStage().setTitle(Translate.haveIt(LabelName.TITLE, lang.label));
        AffichageIRC.setText(Translate.haveIt(TitleName.LANGUAGE_TO_MORSE, lang.titleName));
        btFrToLeet.setText(Translate.haveIt(TitleName.LANGUAGE_TO_L33T, lang.titleName));
        btMorseToFr.setText(Translate.haveIt(TitleName.MORSE_TO_LANGUAGE, lang.titleName));
        btTradDirecte.setText(Translate.haveIt(TitleName.DIRECT_TRANSLATE, lang.titleName));
        lbBienvenu.setText(Translate.haveIt(LabelName.WELCOME, lang.label) + " " + username);
        lblTranslate.setText(Translate.haveIt(LabelName.TITLE, lang.label));
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
}

