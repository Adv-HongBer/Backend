package com.example.hongber.user.repository;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.user.entity.UserET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface SignUpRepository extends JpaRepository<UserET, Long> {
    // save
    @Encrypt
    UserET save(UserET userET);

    // delete
}
