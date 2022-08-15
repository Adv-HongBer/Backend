package com.example.hongber.user.repository;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.user.entity.UserET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SignUpRepository extends JpaRepository<UserET, Long> {
    // find
    @Encrypt(selOpt = true)
    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM user WHERE tel = :#{#userInfo.tel} OR email = :#{#userInfo.email}) AS isExists")
    Integer findAlreadySignUpInfo(@Param("userInfo") UserET userInfo);

    // save
    UserET save(UserET userET);

    // delete
}
