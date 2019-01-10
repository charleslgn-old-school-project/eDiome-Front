package com.ircfront.controller;

import com.ircfront.Utils.ControllerUtils;
import com.ircfront.utils.XMLDataFinder;
import com.ircfront.utils.constante.ServerConstante;
import com.ircserv.metier.Server;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import com.ircfront.utils.lang.Lang;
import com.ircfront.utils.lang.Translate;
import com.ircfront.utils.lang.langage.EN;
import com.ircfront.utils.lang.typetrad.ColorName;
import com.ircfront.utils.lang.typetrad.MenuName;
import com.ircfront.start.Main;

import javax.swing.*;
import javax.swing.plaf.OptionPaneUI;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class Dashboardontroller implements Initializable {
    private static double xOffset = 0;
    private static double yOffset = 0;

    @FXML
    private MenuBar mnuBar;
    @FXML
    private Menu mnuMenu, mnuLanguage;
    @FXML
    private Menu mnuStyle, mnuHelp;
    @FXML
    private MenuItem mnuAbout, whiteTheme, blackTheme, pinkTheme, greenTheme;
    @FXML
    private GridPane pnPrincipal;
    @FXML
    private BorderPane pnZoneTravail;
    @FXML
    private Label lbTitre;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;

    private int nbUser;

    public Dashboardontroller(int nbUser) {
        this.nbUser = nbUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createDrawer();

        addServ();
        BorderPane.setAlignment(this.pnZoneTravail, Pos.CENTER);

        //Si l'utilisateur clique sur la zone d'irc, le drawer se fermera
        pnZoneTravail.setOnMouseClicked(e -> drawer.close());
        //windows move management
        mnuBar.setOnMousePressed(this::mousePressed);
        mnuBar.setOnMouseDragged(this::mouseDrag);
        mnuBar.setOnMouseReleased(this::mouseRelease);

        //language Management
        Main.setLangue(getLang(XMLDataFinder.getLangage()));
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                translate();
            }
        }.start();
    }

    /**
     * Create a drawer with all these event
     */
    private void createDrawer() {
        HamburgerBackArrowBasicTransition burgertask = new HamburgerBackArrowBasicTransition(hamburger);
        burgertask.setRate(-1);
        drawer.setOnDrawerClosing(e -> {
            drawer.setOnDrawerClosed(e2 -> {
                drawer.setPrefWidth(0);
                drawer.setMinWidth(0);
                drawer.setMaxWidth(0);
            });
            this.pnZoneTravail.setPadding(new Insets(0, 0, 0, 0));
            changeBurger(burgertask);
        });

        drawer.setOnDrawerOpening(e -> open(burgertask));

        hamburger.setOnMousePressed(e -> {
            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.toggle();
            }
        });
        drawer.toggle();
    }

    private void open(HamburgerBackArrowBasicTransition burgertask) {
        drawer.setPrefWidth(260);
        drawer.setMinWidth(260);
        drawer.setMaxWidth(260);
        this.pnZoneTravail.setPadding(new Insets(0, 0, 0, 260));
        changeBurger(burgertask);
    }

    /**
     * call when we need to show an IRC
     */
    private void AffichageIRCClick(int nbServ) {
        fadeout(pnZoneTravail);
        fadeout(pnZoneTravail);
        loadFxml(nbServ);

    }

    /**
     * change the menu icon to a row when it is open
     * change the row to a menu icon when it is close
     */
    private void changeBurger(HamburgerBackArrowBasicTransition burgertask) {
        burgertask.setRate(burgertask.getRate() * -1);
        burgertask.play();
    }

    /**
     * load the new stage in the work zone pane
     */
    private void loadFxml(int nbServ) {
        try {

            pnZoneTravail.getChildren().clear();
            IRCController ircController = new IRCController(nbServ, this.nbUser);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/IRC.fxml"));
            loader.setController(ircController);
            Pane mainPane = loader.load();
            pnZoneTravail.setCenter(mainPane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * transition effect
     *
     * @param pane : Pane (Work Zone) where it will display the content
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
     * call to move the window
     */
    private void mouseDrag(MouseEvent event) {
        Main.getPrimaryStage().setMaximized(false);
        setOpacity(0.8);

        if (Main.getPrimaryStage().getY() != event.getScreenY()) {
            Main.getPrimaryStage().setX(event.getScreenX() - xOffset);
            Main.getPrimaryStage().setY(event.getScreenY() - yOffset);
        }
    }

    /**
     * change opacity
     */
    private void setOpacity(double opacity) {
        pnPrincipal.getScene().getWindow().setOpacity(opacity);
    }

    /**
     * replace the window if it is out of the screen
     */
    private void mouseRelease(MouseEvent event) {
        mouseClicked(event);
        if (Main.getPrimaryStage().getY() < 0) {
            Main.getPrimaryStage().setY(0);
        }
        setOpacity(1);
    }

    /**
     * move the window
     */
    private void mousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    /**
     * maximized the window
     */
    private void mouseClicked(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                Main.getPrimaryStage().setMaximized(!Main.getPrimaryStage().isMaximized());
            }
        }
    }

    /**
     * close application
     */
    public void close() {
        ((Stage) pnPrincipal.getScene().getWindow()).close();
    }

    /**
     * maximize palication size
     */
    @FXML
    public void Maximize() {
        if (((Stage) pnPrincipal.getScene().getWindow()).isMaximized()) {
            ((Stage) pnPrincipal.getScene().getWindow()).setMaximized(false);
        } else {
            ((Stage) pnPrincipal.getScene().getWindow()).setMaximized(true);
        }
    }


    /**
     * shrink application
     */
    @FXML
    public void Shrink() {
        ((Stage) pnPrincipal.getScene().getWindow()).setIconified(true);
    }

    /**
     * translate component
     */
    private void translate() {
        Lang lang = Main.getLangue();

        whiteTheme.setText(Translate.haveIt(ColorName.WHITE, lang.themeName));
        blackTheme.setText(Translate.haveIt(ColorName.BLACK, lang.themeName));
        pinkTheme.setText(Translate.haveIt(ColorName.PINK, lang.themeName));
        greenTheme.setText(Translate.haveIt(ColorName.GREEN, lang.themeName));

        mnuStyle.setText(Translate.haveIt(MenuName.MENU_STYLE, lang.menu));
        mnuMenu.setText(Translate.haveIt(MenuName.MENU_MENU, lang.menu));
        mnuAbout.setText(Translate.haveIt(MenuName.MENU_ABOUT, lang.menu));
        mnuHelp.setText(Translate.haveIt(MenuName.MENU_HELP, lang.menu));
        mnuLanguage.setText(Translate.haveIt(MenuName.MENU_LANGUAGE, lang.menu));
    }

    /**
     * change language
     */
    @FXML
    public void changeLanguage(ActionEvent event) {
        Object node = event.getSource();
        if (node instanceof MenuItem) {
            MenuItem mnu = (MenuItem) node;
            String language = (String) mnu.getUserData();
            Lang lang = getLang(language);
            Main.setLangue(lang);
            XMLDataFinder.setLangage(language);

        }
    }

    public void disconnect(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/Connection.fxml"));
        XMLDataFinder.setPassword("");
        ControllerUtils.load(loader);
        ((Stage) pnPrincipal.getScene().getWindow()).close();
    }

    /**
     * get the language
     */
    private Lang getLang(String language) {
        try {
            Class<?> clazz = Class.forName("com.ircfront.utils.lang.langage." + language.toUpperCase());
            Constructor<?> ctor = clazz.getConstructor();
            Object object = ctor.newInstance();
            return (Lang) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new EN();
    }

    /**
     * generate the about window
     */
    @FXML
    public void about() {
        try {
            Stage st = new Stage();
            st.initModality(Modality.NONE);
            st.initOwner(pnPrincipal.getScene().getWindow());
            st.initStyle(StageStyle.UNDECORATED);

            Parent root = FXMLLoader.load(getClass().getResource("../../../gui/propos.fxml"));
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.setResizable(false);
            st.show();
            st.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
                if (!newPropertyValue) {
                    st.close();
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * change color of the application
     */
    @FXML
    public void changeTheme(ActionEvent event) {
        Object node = event.getSource();
        if (node instanceof MenuItem) {
            MenuItem mnu = (MenuItem) node;
            String color = (String) mnu.getUserData();
            Main.getPrimaryStage().getScene().getStylesheets().clear();
            Main.getPrimaryStage().getScene().getStylesheets().add(getClass().getResource("../../../gui/css/main-" + color + ".css").toExternalForm());
            XMLDataFinder.setTheme(color);
        }
    }

    /**
     * generate the about window
     */
    public void addNewServer() {
        try {
            Stage st = new Stage();
            st.initModality(Modality.WINDOW_MODAL);
            st.initOwner(Main.getPrimaryStage().getScene().getWindow());
            st.initStyle(StageStyle.UNDECORATED);
            AddNewServerController addNewServerController = new AddNewServerController(nbUser);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/NewServer.fxml"));
            loader.setController(addNewServerController);
            Pane root = loader.load();
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();

            root.getScene().getWindow().setOnHiding(event -> {
                addServ();
                st.close();
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addServ() {
        List<Server> servers;
        try {
            VBox vBox = new VBox();
            //vBox.setAlignment(Pos.TOP_CENTER);
            vBox.getStyleClass().add("menu-bar-2");
            vBox.setSpacing(20);
            vBox.setPadding(new Insets(10, 10, 0, 10));
            vBox.setAlignment(Pos.TOP_CENTER);

            ImageView iw = new ImageView();

            Image logo = new Image("image/logov4.png");
            //Image logo = new Image("https://www.pngarts.com/files/3/Letter-A-PNG-High-Quality-Image.png", true);
            iw.setImage(logo);
            iw.setFitHeight(34.5);
            iw.setFitWidth(150);
            vBox.getChildren().add(iw);

            servers = ServerConstante.MENU.findServerByUser(nbUser);
            for (Server server : servers) {
                JFXButton jfxButton = new JFXButton(server.getName());
                jfxButton.setPrefSize(Double.MAX_VALUE, 60);
                jfxButton.setOnAction(event -> {
                    AffichageIRCClick(server.getId());
                    lbTitre.setText(server.getName());
                });

                ContextMenu contextMenu = createContextMenu(server);
                jfxButton.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        contextMenu.show(jfxButton, event.getScreenX(), event.getScreenY());
                    }else{
                        contextMenu.hide();
                    }
                });
                vBox.getChildren().add(jfxButton);
            }

            JFXButton addnewserver = new JFXButton("+");
            addnewserver.setButtonType(JFXButton.ButtonType.RAISED);
            addnewserver.getStyleClass().add("addserverbutton");
            addnewserver.setPrefSize(60, 60);
            addnewserver.setOnAction(e -> addNewServer());
            vBox.getChildren().add(addnewserver);


            drawer.setSidePane(vBox);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la fenêtre pour afficher des utilisateurs
     * @param controller
     */
    void Userlist(Object controller) {
        // Ici lance la fenêtre d'ajout utilisateur
        try {
            Stage st = new Stage();
            st.initModality(Modality.WINDOW_MODAL);
            st.initOwner(Main.getPrimaryStage().getScene().getWindow());
            st.initStyle(StageStyle.UNDECORATED);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/adduser.fxml"));
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();

            root.getScene().getWindow().setOnHiding(event2 -> {
                st.close();
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Créé le menu contextuel
     * @return
     */
    private ContextMenu createContextMenu(Server server) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem adduser = new MenuItem("Ajouter un utilisateur");
        adduser.setOnAction(e -> ManageUsers(server.getId(), true, contextMenu));
        MenuItem deluser = new MenuItem("retirer un utilisateur");
        deluser.setOnAction(e -> ManageUsers(server.getId(), false, contextMenu));
        MenuItem quitserv = new MenuItem("Quitter le serveur");
        quitserv.setOnAction(e -> System.out.println("Méthode à faire"));
        MenuItem delserv = new MenuItem("Supprimer le serveur");
        delserv.setOnAction(e -> quitServer());
        MenuItem quitServ = new MenuItem("Supprimer le serveur");
        quitServ.setOnAction(e -> quitServer());
        contextMenu.getItems().addAll(adduser, deluser, quitserv, delserv);
        return contextMenu;
    }

    /**
     * Ajout d'un utilisateur dans un serveur
     * @param nbServ
     */
    private void ManageUsers(int nbServ, boolean typeChoix, ContextMenu contextMenu){
        contextMenu.hide();
        ManageUsersController manageUsersController = new ManageUsersController(nbServ, typeChoix);
        Userlist(manageUsersController);
    }

    /**
     * Supprime le serveur
     * A tester
     */
    private void delServer(Server server, ContextMenu contextMenu){
        try {
            contextMenu.hide();
            int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce serveur", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
            if (reponse == 0) {
                // Supprimer le serveur
                System.out.println("je supprime le serveur" + server.getId());
                ServerConstante.MENU.deleteServer(server.getId());
                Alert succes = new Alert(Alert.AlertType.CONFIRMATION);
                succes.setTitle("Suppression du serveur");
                succes.setHeaderText("Serveur supprimé");
                succes.setContentText("Le serveur a été supprimé avec succès");
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void quitServer(){

    }

}