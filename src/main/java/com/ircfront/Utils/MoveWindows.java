package com.ircfront.Utils;

import com.ircfront.start.Main;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MoveWindows {
  /**
   * call to move the window
   */
  public static void mouseDrag(MouseEvent event, double xOffset, double yOffset) {
    if (Main.getPrimaryStage().getY() != event.getScreenY()) {
      Main.getPrimaryStage().setX(event.getScreenX() - xOffset);
      Main.getPrimaryStage().setY(event.getScreenY() - yOffset);
    }
  }

  /**
   * replace the window if it is out of the screen
   */
  public static void mouseRelease(MouseEvent event) {
    if (Main.getPrimaryStage().getY() < 0) {
      Main.getPrimaryStage().setY(0);
    }
  }

  /**
   * move the window
   */
  public static void mousePressed(MouseEvent event, double xOffset, double yOffset) {
    xOffset = event.getSceneX();
    yOffset = event.getSceneY();
  }
}
