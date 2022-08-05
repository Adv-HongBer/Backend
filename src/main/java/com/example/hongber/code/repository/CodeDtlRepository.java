package com.example.hongber.code.repository;

import com.example.hongber.code.entity.CodeDtlET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDtlRepository extends JpaRepository<CodeDtlET, Long> {
    // find

    // delete
}
