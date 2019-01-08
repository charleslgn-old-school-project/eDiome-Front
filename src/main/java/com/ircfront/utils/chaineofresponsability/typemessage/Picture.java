package com.ircfront.utils.chaineofresponsability.typemessage;

import com.ircfront.utils.chaineofresponsability.NodeFinder;
import com.ircserv.metier.Message;
import com.ircserv.metier.PieceJointe;
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
public class Picture extends NodeFinder {

  public Picture(NodeFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  protected Node resolve1(Message message) {
    try {
      PieceJointe pj = message.getId_pj();
      if (pj != null && pj.getId_type_pj().getLibelle().equals("picture")) {
        String res = message.getId_pj().getChemin();
        ImageView imageView = new ImageView();
        File file = new File(res);
        Image img = new Image(file.toURI().toString());

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
