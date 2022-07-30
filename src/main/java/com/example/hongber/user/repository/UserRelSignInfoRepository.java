package com.example.hongber.user.repository;

import com.example.hongber.user.entity.UserRelSignInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRelSignInfoRepository extends JpaRepository<UserRelSignInfoEntity, Long> {
    // find
    Optional<UserRelSignInfoEntity> findByUserIdx(Long userIdx);

    // delete
}
