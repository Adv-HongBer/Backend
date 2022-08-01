package com.example.hongber.user.repository;

import com.example.hongber.user.entity.UserRelSignInfoET;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRelSignInfoRepository extends JpaRepository<UserRelSignInfoET, Long> {
    // find
    Optional<UserRelSignInfoET> findByUserIdx(Long userIdx);

    // delete
}
