package com.example.hongber.user.service;

import com.example.hongber.common.exception.BaseException;
import com.example.hongber.common.exception.msg.ErrorMsg;
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

    @Transactional(readOnly = true)
    public UserET signIn(SignInReqDTO signInReqDTO) {
        UserET userInfo = UserET.builder()
                .userId(signInReqDTO.getUserId())
                .pass(signInReqDTO.getPass())
                .build();

        try {
            return signInRepository.findByUserIdAndPass(userInfo);
        } catch (Exception e) {
            throw new BaseException(ErrorMsg.SIGNUP_FAIL.getMsg());
        }
    }
}
