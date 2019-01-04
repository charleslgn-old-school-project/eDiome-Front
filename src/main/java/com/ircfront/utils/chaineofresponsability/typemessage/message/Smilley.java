package com.ircfront.utils.chaineofresponsability.typemessage.message;

import com.ircfront.utils.ControllerUtils;
import com.vdurmont.emoji.EmojiParser;
import javafx.scene.Node;

/**
 * Part of a chain of Responsability
 * if the object is a smiley -> return Image of the coresponded smiley
 * else                      -> retunr null (to continue)
 */
public class Smilley extends WordFinder {

  public Smilley(WordFinder expertSuivant) {
    super(expertSuivant);
  }

  @Override
  public Node resolve1(String label) {
    String testSmilley = EmojiParser.parseToAliases(label);
    testSmilley = EmojiParser.parseToHtmlHexadecimal(EmojiParser.parseToUnicode(testSmilley));
    try {
      return ControllerUtils.getEmoji(testSmilley);
    } catch (Exception e) {
      return null;
    }
  }
}
