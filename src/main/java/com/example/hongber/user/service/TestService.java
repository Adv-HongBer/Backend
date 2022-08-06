package com.example.hongber.user.service;

import com.example.hongber.user.dto.UserDTO;
import com.example.hongber.user.entity.UserET;
import com.example.hongber.user.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final SignUpRepository signUpRepository;

    @Transactional
    public void saveTest(UserDTO userDTO) {
        UserET tmpUserInfo = UserET.builder().userId(userDTO.getUserId()).pass(userDTO.getPass()).tel(userDTO.getTel()).email(userDTO.getEmail()).userNm(userDTO.getUserNm()).nickNm(userDTO.getNickNm()).userTypeIdx(userDTO.getUserTypeIdx()).userStatusIdx(userDTO.getUserStatusIdx()).build();

        signUpRepository.save(tmpUserInfo);
    }

    public List<UserET> findTest(UserDTO userDTO) {
        return signUpRepository.findByUserId(userDTO.getUserId());
    }
}
