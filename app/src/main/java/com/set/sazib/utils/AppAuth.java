package com.set.sazib.utils;

import com.set.sazib.BuildConfig;
import com.set.sazib.utils.logger.AppLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppAuth {

  public static String keyHash() {
    final String MD5 = "MD5";

    try {
      String param = BuildConfig.API_KEY
          + ":"
          + BuildConfig.API_TOKEN
          + ":"
          + System.currentTimeMillis() / 1000;

      // Create MD5 Hash
      MessageDigest digest = java.security.MessageDigest
          .getInstance(MD5);
      digest.update(param.getBytes());
      byte[] messageDigest = digest.digest();

      // Create Hex String
      StringBuilder hexString = new StringBuilder();
      for (byte aMessageDigest : messageDigest) {
        String h = Integer.toHexString(0xFF & aMessageDigest);
        while (h.length() < 2) {
          h = "0" + h;
        }
        hexString.append(h);
      }
      AppLogger.d(hexString);
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      AppLogger.d(e.getMessage());
    }
    return "";
  }
  //END of auth key hash Generator
}
