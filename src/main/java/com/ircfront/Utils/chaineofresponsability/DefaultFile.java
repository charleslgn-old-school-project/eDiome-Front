package com.ircfront.Utils.chaineofresponsability;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Part of a chain of Responsability
 * if the object is a file  but not a picture or an audio -> return a link to the file
 * else                                                   -> retunr null (to continue)
 */
public class DefaultFile extends NodeFinder{

  public DefaultFile(NodeFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  public Node resolve1(String label) {
    try {
      if(label.matches(":file:[\\d\\D\\w]+")){
        String res = label.replaceFirst(":file:", "");
        ImageView imageView = new ImageView();
        File file = new File(res);
        Image img = new Image("image/fileLogo.png");
        HBox hobx = new HBox();

        imageView.setImage(img);
        imageView.setFitWidth(48);
        imageView.setPreserveRatio(true);

        Hyperlink hyperText = new Hyperlink(res.substring(res.lastIndexOf("\\")+1));

        hyperText.setOnMouseClicked(e -> browse(file));
        hobx.setOnMouseClicked(e -> browse(file));
        imageView.setOnMouseClicked(e -> browse(file));

        hobx.getChildren().addAll(imageView, hyperText);
        hobx.setSpacing(10);

        return hobx;
      }
    } catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  private void browse(File file){
    try {
      Desktop.getDesktop().browse(file.toURI());
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
}
