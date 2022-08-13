package com.example.hongber.user.service;

import com.example.hongber.user.dto.SignUpReqDTO;
import com.example.hongber.user.entity.UserET;
import com.example.hongber.user.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {
    private final SignUpRepository signUpRepository;

    @Transactional
    public void SingUp(SignUpReqDTO signUpReqDTO) {
        UserET userInfo = UserET.builder()
                .userId(signUpReqDTO.getUserId())
                .pass(signUpReqDTO.getPass())
                .tel(signUpReqDTO.getTel())
                .email(signUpReqDTO.getEmail())
                .userNm(signUpReqDTO.getUserNm())
                .nickNm(signUpReqDTO.getNickNm())
                .build();

        signUpRepository.save(userInfo);
    }
}
