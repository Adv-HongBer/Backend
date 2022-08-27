package com.example.hongber.member.repository;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.member.entity.MemberET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SignInRepository extends JpaRepository<MemberET, Long> {
    // find
    @Encrypt(selOpt = true)
    @Query(nativeQuery = true, value = "SELECT * FROM member WHERE memberId = :#{#memberInfo.memberId} AND pass = :#{#memberInfo.pass}")
    MemberET findByMemberIdAndPass(@Param("memberInfo") MemberET memberInfo);

    // delete
}
