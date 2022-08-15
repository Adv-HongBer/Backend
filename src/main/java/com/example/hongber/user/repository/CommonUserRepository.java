package com.example.hongber.user.repository;

import com.example.hongber.user.entity.UserET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommonUserRepository extends JpaRepository<UserET, Long> {
    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM user WHERE nickNm = :#{#nickNm}) AS isExists")
    Integer chkNickNameIsAlreadyExists(@Param("nickNm") String nickNm);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM user WHERE tel = :#{#tel}) AS isExists")
    Integer chkTelIsAlreadyExists(@Param("tel") String tel);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM user WHERE email = :#{#email}) AS isExists")
    Integer chkEmailIsAlreadyExists(@Param("email") String email);
}
