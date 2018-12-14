package com.ircfront.start;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

public class StartJustResources {
  public static void main(String[] args){
    try {

      System.out.println(Paths.get(new URI("src/main/java/resources/emoji")).toString());

      File[] listOfFiles  = new File("emoji").listFiles();

      for (int i = 0; i < listOfFiles.length; i++) {
        if (listOfFiles[i].isFile()) {
          System.out.println("File " + listOfFiles[i].getName());
        }
      }
    } catch (Exception e){
      e.printStackTrace();
    }

  }
}
