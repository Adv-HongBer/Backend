package com.example.hongber.user.repository;

import com.example.hongber.user.entity.UserRelSignInfoET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRelSignInfoRepository extends JpaRepository<UserRelSignInfoET, Long> {
    // find
    Optional<UserRelSignInfoET> findByUserIdx(Long userIdx);

    // delete
}
