package test.java;

import com.vdurmont.emoji.EmojiParser;

public class TestEmojiParser {
  public static void main(String[] args){
    String str = "An 😀awesome 😃string with a few 😉emojis!";

    String resultDecimal = EmojiParser.parseToHtmlDecimal(str);
    System.out.println(resultDecimal);
    // Prints:
    // "An &#128512;awesome &#128515;string with a few &#128521;emojis!"

    String resultHexadecimal = EmojiParser.parseToHtmlHexadecimal(str);
    System.out.println(resultHexadecimal);
    // Prints:
    // "An &#x1f600;awesome &#x1f603;string with a few &#x1f609;emojis!"
  }
}
