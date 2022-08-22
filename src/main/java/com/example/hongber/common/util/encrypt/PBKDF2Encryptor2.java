package com.example.hongber.common.util.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.util.EncodingUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@Component
public class PBKDF2Encryptor2 implements PasswordEncoder {
    private static final int DEFAULT_SALT_LENGTH = 8;
    private static final int DEFAULT_HASH_WIDTH = 256;
    private static final int DEFAULT_ITERATIONS = 185000;
    private final BytesKeyGenerator saltGenerator;
    private final byte[] secret;
    private final int hashWidth;
    private final int iterations;
    private String algorithm;
    private boolean encodeHashAsBase64;

    public PBKDF2Encryptor2() {
        this("");
    }

    public PBKDF2Encryptor2(CharSequence secret) {
        this(secret, 8, 185000, 256);
    }

    public PBKDF2Encryptor2(CharSequence secret, int saltLength) {
        this(secret, saltLength, 185000, 256);
    }

    public PBKDF2Encryptor2(CharSequence secret, int iterations, int hashWidth) {
        this(secret, 8, iterations, hashWidth);
    }

    public PBKDF2Encryptor2(CharSequence secret, int saltLength, int iterations, int hashWidth) {
        this.algorithm = PBKDF2Encryptor2.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA1.name();
        this.secret = Utf8.encode(secret);
        this.saltGenerator = KeyGenerators.secureRandom(saltLength);
        this.iterations = iterations;
        this.hashWidth = hashWidth;
    }

    public void setAlgorithm(SecretKeyFactoryAlgorithm secretKeyFactoryAlgorithm) {
        if (secretKeyFactoryAlgorithm == null) {
            throw new IllegalArgumentException("secretKeyFactoryAlgorithm cannot be null");
        } else {
            String algorithmName = secretKeyFactoryAlgorithm.name();

            try {
                SecretKeyFactory.getInstance(algorithmName);
                this.algorithm = algorithmName;
            } catch (NoSuchAlgorithmException var4) {
                throw new IllegalArgumentException("Invalid algorithm '" + algorithmName + "'.", var4);
            }
        }
    }

    public void setEncodeHashAsBase64(boolean encodeHashAsBase64) {
        this.encodeHashAsBase64 = encodeHashAsBase64;
    }

    public String encode(CharSequence rawPassword) {
        byte[] salt = this.saltGenerator.generateKey();
        byte[] encoded = this.encode(rawPassword, salt);
        return this.encode(encoded);
    }

    private String encode(byte[] bytes) {
        return this.encodeHashAsBase64 ? Base64.getEncoder().encodeToString(bytes) : String.valueOf(Hex.encode(bytes));
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        byte[] digested = this.decode(encodedPassword);
        byte[] salt = EncodingUtils.subArray(digested, 0, this.saltGenerator.getKeyLength());
        return MessageDigest.isEqual(digested, this.encode(rawPassword, salt));
    }

    private byte[] decode(String encodedBytes) {
        return this.encodeHashAsBase64 ? Base64.getDecoder().decode(encodedBytes) : Hex.decode(encodedBytes);
    }

    private byte[] encode(CharSequence rawPassword, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(rawPassword.toString().toCharArray(), EncodingUtils.concatenate(salt, this.secret), this.iterations, this.hashWidth);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(this.algorithm);
            return EncodingUtils.concatenate(salt, skf.generateSecret(spec).getEncoded());
        } catch (GeneralSecurityException var5) {
            throw new IllegalStateException("Could not create hash", var5);
        }
    }

    public enum SecretKeyFactoryAlgorithm {
        PBKDF2WithHmacSHA1,
        PBKDF2WithHmacSHA256,
        PBKDF2WithHmacSHA512;

        SecretKeyFactoryAlgorithm() {
        }
    }
}