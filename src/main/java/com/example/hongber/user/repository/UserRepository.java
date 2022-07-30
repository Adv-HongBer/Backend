package com.example.hongber.user.repository;

import com.example.hongber.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // find
    UserEntity findByIdx(Long userIdx);

    // delete
    void deleteByIdx(Long userIdx);
}
