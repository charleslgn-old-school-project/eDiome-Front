package com.ircfront.utils.chaineofresponsability;

import com.ircserv.metier.Message;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * A chain of responsability to find the corect object in a message
 */
public abstract class NodeFinder implements Finder{
  private NodeFinder suivant;

  public NodeFinder(NodeFinder expertSuivant){
    this.suivant=expertSuivant;
  }

  @Override
  public Node resolve(Message message){
    Node res = this.resolve1(message);
    if (res != null) {
      return res;
    } else if (this.suivant==null){
      return new Label(message.getContenu());
    } else return this.suivant.resolve(message);
  }

  protected abstract Node resolve1(Message message);
}
