package com.example.hongber.common.util.encrypt;

import com.example.hongber.common.exception.BaseException;
import com.example.hongber.common.exception.msg.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

@Slf4j
@Component
public class PBKDF2Encryptor {
    private static String sAlgorithm;
    private static int sIterationCount;
    private static int sKeyLength;
    private static String sSalt;

    @Value("${pbkdf2.algorithm}")
    private void setAlgorithm(String algorithm) {
        sAlgorithm = algorithm;
    }

    @Value("${pbkdf2.iteration-count}")
    private void setIterationCount(int iterationCount) {
        sIterationCount = iterationCount;
    }

    @Value("${pbkdf2.key-length}")
    private void setKeyLength(int keyLength) {
        sKeyLength = keyLength;
    }

    @Value("${pbkdf2.salt}")
    private void setSalt(String salt) {
        sSalt = salt;
    }

    public static String encrypt(String str, String fieldNm) {
        return convert(encryptToBytes(str, fieldNm), fieldNm);
    }

    public static byte[] encryptToBytes(String str) {
        return encryptToBytes(str, "None");
    }

    public static byte[] encryptToBytes(String str, String fieldNm) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(sAlgorithm);
            KeySpec ks = new PBEKeySpec(str.toCharArray(), sSalt.getBytes(StandardCharsets.UTF_8), sIterationCount, sKeyLength);
            return skf.generateSecret(ks).getEncoded();
        } catch (Exception e) {
            log.error("PBKDF2Encryptor : encrypt error!! : Field=[" + fieldNm + "]", e);
            throw new BaseException(ErrorMsg.ENCRYPT_FAIL.getMsg());
        }
    }

    private static String convert(byte[] bytes, String fieldNm) {
        try {
            return String.format("%0" + (bytes.length << 1) + "x", new BigInteger(1, bytes));
        } catch (Exception e) {
            log.error("PBKDF2Encryptor : convert error!! : Field=[" + fieldNm + "]", e);
            throw new BaseException(ErrorMsg.DECRYPT_FAIL.getMsg());
        }
    }
}