package com.ircfront.utils.chaineofresponsability.typemessage.message;

import com.ircserv.metier.Message;
import javafx.scene.Node;

public interface Finder {
  Node resolve(String word);
}
