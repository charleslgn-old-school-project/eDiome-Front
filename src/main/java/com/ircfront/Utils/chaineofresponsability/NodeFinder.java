package com.ircfront.Utils.chaineofresponsability;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * A chain of responsability to find the corect object in a message
 */
public abstract class NodeFinder implements Finder{
  private NodeFinder suivant;

  NodeFinder(NodeFinder expertSuivant){
    this.suivant=expertSuivant;
  }

  @Override
  public Node resolve(String label){
    Node res = this.resolve1(label);
    if (res != null) {
      return res;
    } else if (this.suivant==null){
      return new Label(label);
    } else return this.suivant.resolve(label);
  }

  public abstract Node resolve1(String label);
}
