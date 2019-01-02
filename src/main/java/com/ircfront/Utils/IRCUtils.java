package com.ircfront.Utils;

import com.ircfront.controller.NewUI2Controller;
import com.ircfront.start.Main;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.vdurmont.emoji.EmojiParser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class IRCUtils {

  /**
   * print the colored Image of the emoji
   *
   * @param emoji the Unicode character
   * @return the colored ImageView of the emoji
   */
  public static ImageView getEmoji(String emoji) {
    String emo = EmojiParser.parseToHtmlHexadecimal(emoji);
    emo = emo.substring(3, emo.length() - 1);
    Image img = new Image("emoji/" + emo + ".png");
    ImageView img1 = new ImageView(img);
    img1.setFitWidth(16);
    img1.setFitHeight(16);
    return img1;
  }

  /**
   * produce the sound alert
   */
  public static void makeSound() {
    try {
      MediaPlayer mp = new MediaPlayer(new Media("alert2.mp3"));
      mp.play();
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  /**
   * @param image        the image to get round
   * @param cornerRadius the rounded percentage (not in percent but in pixel)
   * @return a rounded image
   */
  public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
    int w = image.getWidth();
    int h = image.getHeight();
    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2 = output.createGraphics();

    g2.setComposite(AlphaComposite.Src);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.WHITE);
    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

    g2.setComposite(AlphaComposite.SrcAtop);
    g2.drawImage(image, 0, 0, null);

    g2.dispose();

    return output;
  }

  public static void load(FXMLLoader loader, int userId) {
    try {
      NewUI2Controller newUI2Controller = new NewUI2Controller(userId);
      loader.setController(newUI2Controller);
      GridPane root = loader.load();

      Stage stage = new Stage();
      stage.setTitle("Traducteur");
      stage.initStyle(StageStyle.UNDECORATED);

      assert root != null;
      Scene scene = new Scene(root, 1280, 720);
      stage.setScene(scene);
      stage.setTitle("IRC");
      stage.getIcons().add(new Image("image/ediome2.png"));
      stage.initStyle(StageStyle.TRANSPARENT);
      scene.getStylesheets().add("gui/css/main-" + XMLDataFinder.getTheme() + ".css");

      stage.setResizable(true);

      stage.setMinWidth(800);
      stage.setMinHeight(600);
      stage.show();

      Main.setPrimaryStage(stage);

      String os = System.getProperty("os.name").toLowerCase();

      if (os.contains("win")) {
        long lhwnd = com.sun.glass.ui.Window.getWindows().get(1).getNativeWindow();
        Pointer lpVoid = new Pointer(lhwnd);
        WinDef.HWND hwnd = new WinDef.HWND(lpVoid);
        final User32 user32 = User32.INSTANCE;

        int oldStyle = user32.GetWindowLong(hwnd, WinUser.GWL_STYLE);
        int newStyle = oldStyle | 0x00020000;//WS_MINIMIZEBOX
        user32.SetWindowLong(hwnd, WinUser.GWL_STYLE, newStyle);
      }

      ResizeHelper.addResizeListener(Main.getPrimaryStage());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void load(FXMLLoader loader) {
    try {
      StackPane root = loader.load();
      Stage stage = new Stage();

      assert root != null;
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("IRC");
      stage.getIcons().add(new Image("image/ediome2.png"));
      stage.initStyle(StageStyle.TRANSPARENT);

      stage.setResizable(true);

      stage.show();

      Main.setPrimaryStage(stage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
