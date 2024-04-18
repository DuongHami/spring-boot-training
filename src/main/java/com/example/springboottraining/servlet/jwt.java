package com.example.springboottraining.servlet;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSetTransformer;
import com.nimbusds.jwt.SignedJWT;

import java.util.HashMap;
import java.util.Map;

public class jwt {

    public static String signAndEncryptJwt(String s) {
        return s;
    }

    public static void main(String[] args) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        Map<String, Object> testClaimSet = new HashMap<String, Object>();
        testClaimSet.put("sub", "test");
        testClaimSet.put("iat", System.currentTimeMillis());
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("test")
                .claim("id", "123")
                .build();

        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        System.out.println(signedJWT);
        System.out.println(signAndEncryptJwt("Hello"));
    }
}
