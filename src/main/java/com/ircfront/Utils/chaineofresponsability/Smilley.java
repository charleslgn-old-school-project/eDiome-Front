package com.ircfront.Utils.chaineofresponsability;

import com.ircfront.Utils.IRCUtils;
import com.vdurmont.emoji.EmojiParser;
import javafx.scene.Node;

public class Smilley extends NodeFinder {

  public Smilley(NodeFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  public Node resolve1(String label) {
    String testSmilley = EmojiParser.parseToAliases(label);
    testSmilley = EmojiParser.parseToHtmlHexadecimal(EmojiParser.parseToUnicode(testSmilley));
    try {
      return IRCUtils.getEmoji(testSmilley);
    } catch (Exception e) {
      return null;
    }
  }
}
