package com.example.hongber.member.repository;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.member.entity.MemberET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SignUpRepository extends JpaRepository<MemberET, Long> {
    // find
    @Encrypt(selOpt = true)
    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM member WHERE tel = :#{#memberInfo.tel} OR email = :#{#memberInfo.email}) AS isExists")
    Integer findAlreadySignUpInfo(@Param("memberInfo") MemberET memberInfo);

    // save
    MemberET save(MemberET memberET);

    // delete
}
