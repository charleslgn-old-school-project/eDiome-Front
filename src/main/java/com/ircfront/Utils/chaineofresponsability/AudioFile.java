package com.ircfront.Utils.chaineofresponsability;

import com.ircfront.controller.AudioPlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * Part of a chain of Responsability
 * if the object is an audio file -> return a audio launcher with the audio
 * else                           -> retunr null (to continue)
 */
public class AudioFile extends NodeFinder {

  public AudioFile(NodeFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  public Node resolve1(String label) {
    try {
      if (label.matches(":audio:[\\d\\D\\w]+")) {
        String res = label.replaceFirst(":audio:", "");
        AudioPlayerController fxmlDocumentController = new AudioPlayerController(res);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../gui/audio-player.fxml"));
        fxmlLoader.setController(fxmlDocumentController);
        return fxmlLoader.load();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
