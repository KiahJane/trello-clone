package com.ericsson.trello_clone.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class SecretKeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Generated Secret Key: " + secretKey);
    }
}