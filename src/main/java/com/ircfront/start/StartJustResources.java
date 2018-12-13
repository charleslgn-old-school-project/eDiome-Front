package com.ircfront.start;

import java.io.File;

public class StartJustResources {
  public static void main(String[] args){
    File file = new File("src/main/resources/image/ediome2.png");
    System.out.println(file.toURI());
  }
}
