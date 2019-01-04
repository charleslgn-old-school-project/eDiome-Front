package com.ircfront.utils.chaineofresponsability.typemessage;

import com.ircfront.utils.NodeFinderUtils;
import com.ircfront.utils.chaineofresponsability.NodeFinder;
import com.ircfront.utils.constante.OtherConstante;
import com.ircserv.metier.Message;
import javafx.scene.Node;

/**
 * Part of a chain of Responsability
 * if the object is a file  but not a picture or an audio -> return a link to the file
 * else                                                   -> retunr null (to continue)
 */
public class DefaultFile extends NodeFinder {

  public DefaultFile(NodeFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  protected Node resolve1(Message message) {
    if (OtherConstante.FILE_TYPE.contains(message.getTypeMessage())) {
      return NodeFinderUtils.getFileFormat(message);
    } else {
      return null;
    }
  }
}
