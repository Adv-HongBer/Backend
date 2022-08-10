package com.example.hongber.user.repository;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.user.entity.UserET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SignInRepository extends JpaRepository<UserET, Long> {
    // find
    @Encrypt(selOpt = true)
    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE userId = :#{#userInfo.userId} AND pass = :#{#userInfo.pass}")
    UserET findByUserIdAndPass(@Param("userInfo") UserET userInfo);

    // delete
}
