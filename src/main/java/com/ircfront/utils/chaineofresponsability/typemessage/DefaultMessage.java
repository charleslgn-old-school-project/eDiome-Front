package com.ircfront.utils.chaineofresponsability.typemessage;

import com.ircfront.utils.chaineofresponsability.NodeFinder;
import com.ircfront.utils.chaineofresponsability.typemessage.message.HyperText;
import com.ircfront.utils.chaineofresponsability.typemessage.message.Smilley;
import com.ircfront.utils.chaineofresponsability.typemessage.message.Word;
import com.ircfront.utils.chaineofresponsability.typemessage.message.WordFinder;
import com.ircserv.metier.Message;
import com.vdurmont.emoji.EmojiParser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

public class DefaultMessage extends NodeFinder {

  public DefaultMessage(NodeFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  protected Node resolve1(Message message) {
    WordFinder nodeFinder = new HyperText(
            new Smilley(
                    new Word(null)));

    FlowPane hBoxMessage = new FlowPane();
    String affichage = message.getContenu();
    affichage = EmojiParser.parseToAliases(affichage);
    String[] list = affichage.split(" ");


    for (String s : list) {
      Node node = nodeFinder.resolve(s);
      hBoxMessage.getChildren().add(node);
    }
    hBoxMessage.setHgap(2);
    hBoxMessage.setPadding(new Insets(0, 30, 0, 30));
    hBoxMessage.setAlignment(Pos.CENTER_LEFT);

    return hBoxMessage;
  }
}
