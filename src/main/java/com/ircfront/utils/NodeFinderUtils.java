package com.ircfront.utils;

import com.ircserv.metier.Message;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NodeFinderUtils {
  public static Node getFileFormat(Message message) {
    try {
        String res = message.getId_pj().getChemin();
        ImageView imageView = new ImageView();
        File file = new File(res);
        Image img = new Image("image/icon/"+message.getId_pj().getId_type_pj().getLibelle()+".png");
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
    } catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  private static void browse(File file){
    try {
      Desktop.getDesktop().browse(file.toURI());
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
}
