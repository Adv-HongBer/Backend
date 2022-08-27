package com.example.hongber.member.repository;

import com.example.hongber.member.entity.MemberET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberET, Long> {
    // find
    MemberET findByIdx(Long memberIdx);

    // delete
    void deleteByIdx(Long memberIdx);
}
