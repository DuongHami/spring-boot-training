package com.example.springboottraining.servlet;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSetTransformer;
import com.nimbusds.jwt.SignedJWT;

import java.util.HashMap;
import java.util.Map;

public class jwt {

    public static Map<String, String> generateInput(){
        Map<String, String> dictionary = new HashMap<String, String>();
        dictionary.put("jws","eyJraWQiOiIzMjoyMDQyMiIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI5MDA2Mjc5ODY2QDAxNTEyMjgyNzA1MyIsImF1ZCI6Imh0dHBzOlwvXC90ZXN0LWNpbS5hbGxpYW56LmRlOjQ0M1wvYXV0aFwvb2F1dGgyXC9yZWFsbXNcL3Jvb3RcL3JlYWxtc1wvZXUxIiwic3RhZ2UiOiJ0ZXN0IiwiYXV0aG9yaXphdGlvbl9kZXRhaWxzIjpbeyJ0cmFuc2FjdGlvbkRhdGEiOnsidGVzdCI6InRlc3QxX2RhdGExIiwibm9jaF9laW5fdGVzdCI6W119LCJsb2NhdGlvbnMiOlsiaHR0cHM6XC9cL2xvY2FsaG9zdDo4MDgwXC9zdGFydCJdLCJkaXNwbGF5RGF0YSI6eyJwdXJlVGV4dCI6bnVsbCwidGl0bGUiOiJXZWlobmFjaHRzYm9udXMgYmVzdMOkdGlnZW4iLCJpdGVtcyI6eyJBdXN6YWhsdW5nc3Rlcm1pbiI6IjE0LjA3LjIwMjEiLCJBdXN6YWhsdW5nc2JldHJhZyI6IjguMDAwLDAwIEVVUiJ9LCJwcm9tcHQiOiJCaXR0ZSBiZXN0w6R0aWdlbiBTaWUgZGllIEF1c3phaGx1bmcgSWhyZXMgV2VpaG5hY2h0c2JvbnVzIHp1bSBBcmJlaXRzdmVydHJhZyA8Yj5BVi0xMjM0NTY3ODk8XC9iPi4ifSwidGFuTWVzc2FnZSI6IkJlc3TDpHRpZ3VuZ3Njb2RlIGbDvHIgZGllIEF1c3phaGx1bmcgdm9uIDUwMCwwMCBFVVIgYWxzIFdlaWhuYWNodHNnZWxkICIsInR5cGUiOiJjaW0uc3RhbmRhcmR0cmFuc2FjdGlvbiIsInRyYW5zYWN0aW9uSWQiOiJjOTQ1NGVmZS1hZmNmLTQxNjQtOGJiZS1iYjlhYWI1MWJjNDEifV0sImlzcyI6ImZrb3hhYTFkZ3BudWpudWkwZ2E0bnpwNmx4ZmkxY2QwYmg3dHZ6N3QiLCJleHAiOjE3MTIxNDkzOTgsImlhdCI6MTcxMjE0OTMzOCwianRpIjoiMDNjNzhhYTEtY2M3Yi00ZDI4LTkxNzktOTM4ZTEyMjk0YTljIiwiY2xpZW50X2lkIjoiZmtveGFhMWRncG51am51aTBnYTRuenA2bHhmaTFjZDBiaDd0dno3dCJ9.Os9VpFoJHYAphNAczx_Idp2mD6hZw1QlWj0kz4cFoobh2mVdKoxPAU6BftvX_gA8LrMBKrKYqJkRtzi3XrmGYuZFB9SQXVuhlbm3RWD8LBvOHT1ae4cDABZ_mYh7xy--52JJPgENfhWvCrBfmB5LCC1Bqe8vez4mof5PPuRR6Sqq90TmyMoo_QElr-CAV6pr_N1jHAQtMFXO4ZxW6YCin4dS3EbN6McstjiFgD4tRcxy62W34-vrMM_GMJzLFkDmJQKRX7KFM-BuYqn7mwtdBc19LDar2l3ieQcLjq8K0wOKTBa4x7fwLX9IAj_W316jI3kw8XvNdhev2Q9BzIHpVg");
        dictionary.put("e", "AQAB");
        dictionary.put("n", "wQDVEi6H8d-YBdTqA9pf5ofvBLDSAwqbdDoe53K6UnYWs9BnnKlnXMeJx2jD2aGwaPpdOUoxhrgR0htVmPPVfdGVgB0YlxN157nlsjjA2F-woiVZv3PlwITguN69KU29Rdwj0Exh_SACbAnXCUqIOMZvj92FD9woi9mxMT00hwa5pR5C9j0FEYDCJ29S2QuLa8POvbcN-e5HHNilaNtBAY3tQp1BOE1QSaOmZvvHy3YfRJ8BmyWnjFM_61_G1fe5b2pI-Q4i2EznEECAlO6SBggiWBvTJe_pkbdZrXcjXIp936kKk0359t3DBU614NTeZ7XFOkclqcz5510EssFjow");
        return dictionary;
    }

    public static String signAndEncryptJwt(String s) {
        return s;
    }

    public static void main(String[] args) {
        Map<String, String> input_dict = generateInput();
        System.out.println(input_dict);

//        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
//
//        Map<String, Object> testClaimSet = new HashMap<String, Object>();
//        testClaimSet.put("sub", "test");
//        testClaimSet.put("iat", System.currentTimeMillis());
//        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//                .subject("test")
//                .claim("id", "123")
//                .build();
//
//        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
//        System.out.println(signedJWT);
//
//        System.out.println(signAndEncryptJwt("Hello"));
    }
}
