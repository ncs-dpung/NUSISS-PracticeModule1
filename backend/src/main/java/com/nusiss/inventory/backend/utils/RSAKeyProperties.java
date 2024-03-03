package com.nusiss.inventory.backend.utils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RSAKeyProperties {

  private RSAPublicKey publicKey;
  private RSAPrivateKey privateKey;

  public RSAKeyProperties() {
    KeyPair keyPair = KeyGeneratorUtil.generateRsaKey();
    this.publicKey = (RSAPublicKey) keyPair.getPublic();
    this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
  }
}
