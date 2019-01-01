package com.ircfront.Utils.chaineofresponsability;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HyperText extends NodeFinder {
  private final String regExp = "^(http(s|)://(www\\.|)|www\\.)[a-z0-9]+([\\-.][a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$";

  public HyperText(NodeFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  public Node resolve1(String label) {
    if(label.matches(regExp)) {
      Hyperlink hyperlink = new Hyperlink(label);
      hyperlink.setOnAction(e -> {
        try {
          Desktop.getDesktop().browse(new URI(label));
        } catch (URISyntaxException | IOException e1) {
          e1.printStackTrace();
        }
      });
      return hyperlink;
    } else return null;
  }
}
