package com.example.hongber.user.repository;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.common.enumeration.EncryptType;
import com.example.hongber.user.entity.UserET;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SignUpRepository extends Repository<UserET, Long> {
    // find
    @Encrypt(selOpt = true)
    List<UserET> findByUserId(String userId);

    // save
    @Encrypt(EncryptType.AES128)
    void save(UserET userET);

    // delete
}
