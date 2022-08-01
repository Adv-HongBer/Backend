package com.example.hongber.common.util;

import com.example.hongber.common.aop.PBKDF2Encryptor;
import com.example.hongber.common.exception.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
@Component
public class AESEncryptor {
    private static final String ALGORITHM = "AES";
    private static final String BLOCK_PADDING_CBC = "/CBC/PKCS5Padding";
    private static final String BLOCK_PADDING_ECB = "/ECB/PKCS5Padding";
    private static String sKey;
    private static SecretKeySpec sks;
    private static SecretKeySpec sks2;
    private static byte[] iv;

    private static final int AES_128_KEY_SIZE = 16;
    private static final int AES_256_KEY_SIZE = 32;
    private static final int IV_SIZE = 16;

    @Value("${jasypt.encryptor.password}")
    private void setKey(String key) {
        sKey = key;
    }

    private static void init() {
        if (sks == null) {
            sks = new SecretKeySpec(PBKDF2Encryptor.encryptToBytes(sKey), ALGORITHM);
        }
    }

    private static void init2() {
        if (sks2 == null) {
            byte[] key = sKey.substring(0, AES_256_KEY_SIZE).getBytes(StandardCharsets.UTF_8);
            iv = sKey.substring(sKey.length() - IV_SIZE).getBytes(StandardCharsets.UTF_8);
            sks2 = new SecretKeySpec(key, ALGORITHM);
        }
    }

    public static String decAes256R(String str, String fieldNm) {
        try {
            init();
            byte[] ivCipherByte = Base64.getDecoder().decode(str.replace("\n",""));
            byte[] iv = Arrays.copyOfRange(ivCipherByte, 0, IV_SIZE);
            byte[] cipherByte = Arrays.copyOfRange(ivCipherByte, IV_SIZE, ivCipherByte.length);
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_CBC);
            cipher.init(Cipher.DECRYPT_MODE, sks, new IvParameterSpec(iv));
            return new String(cipher.doFinal(cipherByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AESEncryptor : decrypt error!! : Field=["+ fieldNm +"]", e);
            throw new RuntimeException(ErrorMsg.ENCRYPT_FAIL.getMsg());
        }
    }

    public static String decAes256R(String str) {
        return decAes256R(str, "None");
    }

    public static String encAes256S(String str, String fieldNm) {
        try {
            init2();
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, sks2, new IvParameterSpec(iv));
            byte[] cipherByte = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherByte);
        } catch (Exception e) {
            log.error("AESEncryptor : encrypt error!! : Field=["+ fieldNm +"]", e);
            throw new RuntimeException(ErrorMsg.ENCRYPT_FAIL.getMsg());
        }
    }

    public static String decAes256S(String str, String fieldNm) {
        try {
            init2();
            byte[] cipherByte = Base64.getDecoder().decode(str.replace("\n",""));
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_CBC);
            cipher.init(Cipher.DECRYPT_MODE, sks2, new IvParameterSpec(iv));
            return new String(cipher.doFinal(cipherByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AESEncryptor : decrypt error!! : Field=["+ fieldNm +"]", e);
            throw new RuntimeException(ErrorMsg.DECRYPT_FAIL.getMsg());
        }
    }

    public static String encAes256K(String key, String str, String fieldNm) {
        try {
            byte[] bKey = key.substring(0, AES_256_KEY_SIZE).getBytes(StandardCharsets.UTF_8);
            byte[] iv = key.substring(0, IV_SIZE).getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(bKey, ALGORITHM), new IvParameterSpec(iv));
            byte[] cipherByte = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherByte);
        } catch (Exception e) {
            log.error("AESEncryptor : encrypt error!! : Field=["+ fieldNm +"]", e);
            throw new RuntimeException(ErrorMsg.ENCRYPT_FAIL.getMsg());
        }
    }

    public static String decAes256K(String key, String str, String fieldNm) {
        try {
            byte[] cipherByte = Base64.getDecoder().decode(str.replace("\n",""));
            byte[] bKey = key.substring(0, AES_256_KEY_SIZE).getBytes(StandardCharsets.UTF_8);
            byte[] iv = key.substring(0, IV_SIZE).getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_CBC);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(bKey, ALGORITHM), new IvParameterSpec(iv));
            return new String(cipher.doFinal(cipherByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AESEncryptor : decrypt error!! : Field=["+ fieldNm +"]", e);
            throw new RuntimeException(ErrorMsg.DECRYPT_FAIL.getMsg());
        }
    }

    public static String encAes128E(String str, String fieldNm) {
        try {
            byte[] bKey = sKey.substring(0, AES_128_KEY_SIZE).getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_ECB);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(bKey, ALGORITHM));
            byte[] cipherByte = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherByte);
        } catch (Exception e) {
            log.error("AESEncryptor : encrypt error!! : Field=["+ fieldNm +"]", e);
            throw new RuntimeException(ErrorMsg.ENCRYPT_FAIL.getMsg());
        }
    }

    public static String decAes128E(String str, String fieldNm) {
        try {
            byte[] cipherByte = Base64.getDecoder().decode(str.replace("\n",""));
            byte[] bKey = sKey.substring(0, AES_128_KEY_SIZE).getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_ECB);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(bKey, ALGORITHM));
            return new String(cipher.doFinal(cipherByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AESEncryptor : decrypt error!! : Field=["+ fieldNm +"]", e);
            throw new RuntimeException(ErrorMsg.DECRYPT_FAIL.getMsg());
        }
    }

    public static String encAes128K(String key, String str, String fieldNm) {
        try {
            byte[] bKey = key.substring(0, AES_128_KEY_SIZE).getBytes(StandardCharsets.UTF_8);
            byte[] iv = key.substring(0, IV_SIZE).getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(bKey, ALGORITHM), new IvParameterSpec(iv));
            byte[] cipherByte = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherByte);
        } catch (Exception e) {
            log.error("AESEncryptor : encrypt error!! : Field=["+ fieldNm +"]", e);
            throw new RuntimeException(ErrorMsg.ENCRYPT_FAIL.getMsg());
        }
    }

    public static String decAes128K(String key, String str, String fieldNm) {
        try {
            byte[] cipherByte = Base64.getDecoder().decode(str.replace("\n",""));
            byte[] bKey = key.substring(0, AES_128_KEY_SIZE).getBytes(StandardCharsets.UTF_8);
            byte[] iv = key.substring(0, IV_SIZE).getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_CBC);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(bKey, ALGORITHM), new IvParameterSpec(iv));
            return new String(cipher.doFinal(cipherByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AESEncryptor : decrypt error!! : Field=["+ fieldNm +"]", e);
            throw new RuntimeException(ErrorMsg.DECRYPT_FAIL.getMsg());
        }
    }
}