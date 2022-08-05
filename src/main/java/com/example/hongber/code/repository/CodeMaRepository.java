package com.example.hongber.code.repository;

import com.example.hongber.code.entity.CodeMaET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeMaRepository extends JpaRepository<CodeMaET, Long> {
    // find

    // delete
}
