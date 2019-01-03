package com.ircfront.Utils.chaineofresponsability;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Part of a chain of Responsability
 * if the object is a picture -> return an ImageView of the picture
 * else                       -> retunr null (to continue)
 */
public class Picture extends NodeFinder{

  public Picture(NodeFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  public Node resolve1(String label) {
    try {
      if(label.matches(":image:[\\d\\D\\w]+")){
        String res = label.replaceFirst(":image:", "");
        ImageView imageView = new ImageView();
        File file = new File(res);
        Image img = new Image(file.toURI().toString(), true);

        imageView.setImage(img);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);

        imageView.setOnMouseClicked(e -> {
          try {
            Desktop.getDesktop().browse(file.toURI());
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        });

        return imageView;
      }
    } catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
