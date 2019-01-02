package com.ircfront.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

  public static String hash(String passwordToHash) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();
    for(int i=0; i< hash.length ;i++)
    {
      sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
    }
    System.out.println(sb.toString());
    return sb.toString();
  }
}
