package com.ircfront.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

  public static String hash(String passwordToHash) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();
    for (byte hash1 : hash) {
      sb.append(Integer.toString((hash1 & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }
}
