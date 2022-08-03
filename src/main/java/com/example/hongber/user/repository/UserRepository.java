package com.example.hongber.user.repository;

import com.example.hongber.user.entity.UserET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserET, Long> {
    // find
    UserET findByIdx(Long userIdx);

    // delete
    void deleteByIdx(Long userIdx);
}