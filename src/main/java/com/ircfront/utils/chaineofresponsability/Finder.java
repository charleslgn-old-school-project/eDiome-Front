package com.ircfront.utils.chaineofresponsability;

import com.ircserv.metier.Message;
import javafx.scene.Node;

public interface Finder {
  Node resolve(Message message);
}
