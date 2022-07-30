package com.example.hongber.user.repository;

import com.example.hongber.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignInRepository extends JpaRepository<UserEntity, Long> {
    // find

    // delete
}
