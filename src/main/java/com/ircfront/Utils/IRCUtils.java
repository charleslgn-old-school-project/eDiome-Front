package com.ircfront.Utils;

import com.vdurmont.emoji.EmojiParser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class IRCUtils {

  /**
   * print the colored Image of the emoji
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
  public static void makeSound(){
    try {
      MediaPlayer mp = new MediaPlayer(new Media("alert2.mp3"));
      mp.play();
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
