package com.example.hongber.member.repository;

import com.example.hongber.member.entity.MemberRelSignInfoET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRelSignInfoRepository extends JpaRepository<MemberRelSignInfoET, Long> {
    // find
    Optional<MemberRelSignInfoET> findByMemberIdx(Long memberIdx);

    // delete
}
