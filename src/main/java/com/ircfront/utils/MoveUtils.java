package com.ircfront.utils;

import com.ircfront.start.Main;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MoveUtils {
    private static double xOffset = 0;
    private static double yOffset = 0;

    public static void moveEvent(Node node){
        node.setOnMousePressed(MoveUtils::mousePressed);
        node.setOnMouseDragged(MoveUtils::mouseDrag);
        node.setOnMouseReleased(e -> MoveUtils.mouseRelease());
    }

    /**
     * call to move the window
     */
    private static void mouseDrag(MouseEvent event) {
        setOpacity(0.8);
        Main.getPrimaryStage().setMaximized(false);
        if (Main.getPrimaryStage().getY() != event.getScreenY()) {
            Main.getPrimaryStage().setX(event.getScreenX() - xOffset);
            Main.getPrimaryStage().setY(event.getScreenY() - yOffset);
        }
    }

    /**
     * replace the window if it is out of the screen
     */
    private static void mouseRelease() {
        if (Main.getPrimaryStage().getY() < 0) {
            Main.getPrimaryStage().setY(0);
        }
        setOpacity(1);
    }

    /**
     * move the window
     */
    private static void mousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    private static void setOpacity(double opacity) {
        Main.getPrimaryStage().getScene().getWindow().setOpacity(opacity);
    }
}
