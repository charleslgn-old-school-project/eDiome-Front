package com.ircfront.utils.chaineofresponsability.typemessage.message;

import com.ircfront.utils.chaineofresponsability.NodeFinder;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Part of a chain of Responsability
 * the default res -> return the text
 */
public class Word extends WordFinder {

  public Word(WordFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  public Node resolve1(String label) {
    return new Label(label);
  }
}
