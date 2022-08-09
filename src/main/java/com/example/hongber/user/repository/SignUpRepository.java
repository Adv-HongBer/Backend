package com.example.hongber.user.repository;

import com.example.hongber.user.entity.UserET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignUpRepository extends JpaRepository<UserET, Long> {
    // find
    List<UserET> findByUserId(String userId);

    // delete
}
