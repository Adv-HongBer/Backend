package com.example.hongber.user.repository;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.user.entity.UserET;
import org.springframework.data.repository.Repository;

public interface SignUpRepository extends Repository<UserET, Long> {
    // save
    @Encrypt
    void save(UserET userET);

    // delete
}
