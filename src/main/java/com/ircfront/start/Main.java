package com.ircfront.start;

import com.ircfront.Utils.ResizeHelper;
import com.ircfront.Utils.XMLDataFinder;
import com.ircfront.controller.IRCController;
import com.ircfront.controller.NewUI2Controller;
import com.ircserv.inter.MenuInterface;
import com.ircserv.metier.Constante;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.ircfront.lang.Lang;

import java.io.File;
import java.net.PasswordAuthentication;
import java.rmi.Naming;

public class Main extends Application {

  private static Lang langue;

  private static Stage prStage;

  private static String[] val;

  public static void main(String[] args) {
    val = args;
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      com.sun.javafx.util.Logging.getJavaFXLogger().setLevel(sun.util.logging.PlatformLogger.Level.OFF);

      prStage = primaryStage;

      FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../gui/connection.fxml"));
      Parent root = loader.load();

      Font.loadFont("Roboto-Black.ttf", 10);
      primaryStage.getIcons().add(new Image(getClass().getResource("../../../image/ediome2.png").toString()));
      primaryStage.initStyle(StageStyle.UNDECORATED);
      primaryStage.setScene(new Scene(root));

      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Stage getPrimaryStage() {
    return prStage;
  }

  public static void setPrimaryStage(Stage prStage) {
    Main.prStage = prStage;
  }

  public static Lang getLangue() {
    return langue;
  }

  public static void setLangue(Lang langue) {
    Main.langue = langue;
  }
}
