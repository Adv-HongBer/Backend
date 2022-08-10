package com.example.hongber.user.service;

import com.example.hongber.user.dto.SignInReqDTO;
import com.example.hongber.user.entity.UserET;
import com.example.hongber.user.repository.SignInRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignInService {
    private final SignInRepository signInRepository;

    public UserET signIn(SignInReqDTO signInReqDTO) {
        UserET userInfo = UserET.builder()
                .userId(signInReqDTO.getUserId())
                .pass(signInReqDTO.getPass())
                .build();

        return signInRepository.findByUserIdAndPass(userInfo);
    }
}
