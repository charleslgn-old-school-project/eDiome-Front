package com.ircfront.utils.chaineofresponsability.typemessage;

import com.ircfront.controller.AudioPlayerController;
import com.ircfront.utils.chaineofresponsability.NodeFinder;
import com.ircserv.metier.Message;
import com.ircserv.metier.PieceJointe;
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
  protected Node resolve1(Message message) {
    try {
      PieceJointe pj = message.getPieceJointe();
      if (pj != null && pj.getTypePieceJointe().getLibelle().equals("audio")) {
        String res = message.getPieceJointe().getChemin();
        AudioPlayerController fxmlDocumentController = new AudioPlayerController(res);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/../../gui/audio-player.fxml"));
        fxmlLoader.setController(fxmlDocumentController);
        return fxmlLoader.load();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
