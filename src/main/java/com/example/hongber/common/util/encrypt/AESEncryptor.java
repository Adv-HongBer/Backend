package com.example.hongber.common.util.encrypt;

import com.example.hongber.common.exception.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
public class AESEncryptor {
    private static final String ALGORITHM = "AES";
    private static final String BLOCK_PADDING_ECB = "/ECB/PKCS5Padding";
    private static String sKey;

    private static final int AES_128_KEY_SIZE = 16;

    @Value("${jasypt.encryptor.password}")
    private void setKey(String key) {
        sKey = key;
    }

    public static String encAes128E(String str) {
        return encAes128E(str, "None");
    }
    public static String decAes128E(String str) {
        return decAes128E(str, "None");
    }

    public static String encAes128E(String str, String fieldNm) {
        try {
            byte[] bKey = sKey.substring(0, AES_128_KEY_SIZE).getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_ECB);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(bKey, ALGORITHM));
            byte[] cipherByte = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherByte);
        } catch (Exception e) {
            log.error("AESEncryptor : encrypt error!! : Field=[" + fieldNm + "]", e);
            throw new RuntimeException(ErrorMsg.ENCRYPT_FAIL.getMsg());
        }
    }
    public static String decAes128E(String str, String fieldNm) {
        try {
            byte[] cipherByte = Base64.getDecoder().decode(str.replace("\n", ""));
            byte[] bKey = sKey.substring(0, AES_128_KEY_SIZE).getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(ALGORITHM + BLOCK_PADDING_ECB);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(bKey, ALGORITHM));
            return new String(cipher.doFinal(cipherByte), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("AESEncryptor : decrypt error!! : Field=[" + fieldNm + "]", e);
            throw new RuntimeException(ErrorMsg.DECRYPT_FAIL.getMsg());
        }
    }
}
