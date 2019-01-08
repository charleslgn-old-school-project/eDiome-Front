package com.ircfront.utils.chaineofresponsability.typemessage.message;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * A chain of responsability to find the corect object in a message
 */
public abstract class WordFinder implements Finder {
  private WordFinder suivant;

  public WordFinder(WordFinder expertSuivant){
    this.suivant=expertSuivant;
  }

  @Override
  public Node resolve(String message){
    Node res = this.resolve1(message);
    if (res != null) {
      return res;
    } else if (this.suivant==null){
      return new Label(message);
    } else return this.suivant.resolve(message);
  }

  public abstract Node resolve1(String message);
}
