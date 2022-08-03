package com.example.hongber.common.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
public class JasyptConfig {
    @Value("${jasypt.encryptor.algorithm}")
    private String algorithm;

    @Value("${jasypt.encryptor.key-obtention-iterations}")
    private int keyObtentionIterations;

    @Value("${jasypt.encryptor.pool-size}")
    private int poolSize;

    @Value("${jasypt.encryptor.password}")
    private String PASSWORD;

    @Bean("jasyptStringEncryptor")
    public PooledPBEStringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setKeyObtentionIterations(keyObtentionIterations);
        encryptor.setPoolSize(poolSize);
        encryptor.setPassword(PASSWORD);
        encryptor.setAlgorithm(algorithm);
        return encryptor;
    }
}