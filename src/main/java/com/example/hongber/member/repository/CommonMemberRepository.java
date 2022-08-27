package com.example.hongber.member.repository;

import com.example.hongber.member.entity.MemberET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommonMemberRepository extends JpaRepository<MemberET, Long> {
    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM member WHERE nickNm = :#{#nickNm}) AS isExists")
    Integer chkNickNameIsAlreadyExists(@Param("nickNm") String nickNm);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM member WHERE tel = :#{#tel}) AS isExists")
    Integer chkTelIsAlreadyExists(@Param("tel") String tel);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM member WHERE email = :#{#email}) AS isExists")
    Integer chkEmailIsAlreadyExists(@Param("email") String email);
}
