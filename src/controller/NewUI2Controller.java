package controller;

import Utils.XMLDataFinder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
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
import resource.lang.Lang;
import resource.lang.Translate;
import resource.lang.langage.EN;
import resource.lang.typetrad.ColorName;
import resource.lang.typetrad.MenuName;
import start.Main;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ResourceBundle;

public class NewUI2Controller implements Initializable {
  private static double xOffset = 0;
  private static double yOffset = 0;

  @FXML
  private MenuBar mnuBar;
  @FXML
  private Menu mnuMenu,
               mnuLanguage;
  @FXML
  private Menu mnuStyle,
               mnuHelp;
  @FXML
  private MenuItem mnuAbout,
                   whiteTheme,
                   blackTheme,
                   pinkTheme,
                   greenTheme;
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

    drawer.setOnDrawerOpening(e -> {
      drawer.setPrefWidth(260);
      drawer.setMinWidth(260);
      drawer.setMaxWidth(260);
      this.pnZoneTravail.setPadding(new Insets(0, 0, 0, 260));
      changeBurger(burgertask);
    });

    hamburger.setOnMousePressed(e -> {
      if (drawer.isOpened()) {
        drawer.close();
      } else {
        drawer.toggle();
      }
    });
  }

  /**
   * call when we need to show an IRC
   */
  private void AffichageIRCClick(int nbServ) {
    fadeout(pnZoneTravail);
    loadFxml(nbServ);
    fadeout(pnZoneTravail);

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

      IRCController ircController =
              new IRCController(nbServ);

      FXMLLoader loader = new FXMLLoader(
              getClass().getResource("..//gui/IRC.fxml"
              )
      );
      loader.setController(ircController);

      Pane mainPane = loader.load();

      pnZoneTravail.setCenter(mainPane);
    } catch (Exception ex) {
      System.err.println(ex);
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

  /**
   * get the language
   */
  private Lang getLang(String language) {
    try {
      Class<?> clazz = Class.forName("resource.lang.langage." + language.toUpperCase());
      Constructor<?> ctor = clazz.getConstructor();
      Object object = ctor.newInstance();
      return  (Lang) object;
    } catch (Exception e) {
      System.err.println(e);
    }
    return new EN();
  }

  /**
   * generate the about window
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
        if (!newPropertyValue) {
          st.close();
        }
      });

    } catch (Exception ex) {
      System.out.println(ex);
    }
  }

  /**
   * change color of the application
   */
  public void changeTheme(ActionEvent event) {
    Object node = event.getSource();
    if (node instanceof MenuItem) {
      MenuItem mnu = (MenuItem) node;
      String color = (String) mnu.getUserData();
      Main.getPrimaryStage().getScene().getStylesheets().clear();
      Main.getPrimaryStage().getScene().getStylesheets().add(getClass().getResource("..//gui/css/main-" + color + ".css").toExternalForm());
      XMLDataFinder.setTheme(color);
    }
  }

  private void addServ() {
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

    for (int i = 0; i < 4; i++) {
      int finalI = i;

      JFXButton jfxButton = new JFXButton("server" + i);
      jfxButton.setPrefSize(Double.MAX_VALUE, 60);
      jfxButton.setOnAction(event -> AffichageIRCClick(finalI));
      vBox.getChildren().add(jfxButton);
    }
    drawer.setSidePane(vBox);
  }
}