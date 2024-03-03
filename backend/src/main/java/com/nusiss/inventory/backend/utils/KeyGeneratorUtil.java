package com.nusiss.inventory.backend.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtil {

  public static KeyPair generateRsaKey() {

    KeyPair keypair;

    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("rsa");
      keyPairGenerator.initialize(2048);
      keypair = keyPairGenerator.generateKeyPair();
    } catch (Exception e) {
      throw new IllegalStateException();
    }

    return keypair;
  }
}
