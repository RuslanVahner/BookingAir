package com.vahner.airticketsapp.generator;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//
//public class GeneratorJwt {
//
//    private static final Logger log = LoggerFactory.getLogger(GeneratorJwt.class);
//
//    public static void main(String[] args) {
//        try {
//            String jwtAccessKey = generateSecureKey();
//            String jwtRefreshKey = generateSecureKey();
//            log.info("JWT Access Key: {}", jwtAccessKey);
//            log.info("JWT Refresh Key: {}", jwtRefreshKey);
//        } catch (NoSuchAlgorithmException e) {
//            log.error("Error generating secure keys", e);
//        }
//    }
//
//    private static String generateSecureKey() throws NoSuchAlgorithmException {
//        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//        keyGen.init(256);
//        SecretKey secretKey = keyGen.generateKey();
//        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
//    }
//}