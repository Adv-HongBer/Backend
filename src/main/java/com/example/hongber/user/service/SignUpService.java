package com.example.hongber.user.service;

import com.example.hongber.common.constant.ConstExistsCode;
import com.example.hongber.common.exception.BaseException;
import com.example.hongber.common.exception.msg.ErrorMsg;
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
    private final CommonUserService commonUserService;

    @Transactional
    public Long SignUp(SignUpReqDTO signUpReqDTO) {
        UserET userInfo = UserET.builder()
                .userId(signUpReqDTO.getUserId())
                .pass(signUpReqDTO.getPass())
                .tel(signUpReqDTO.getTel())
                .email(signUpReqDTO.getEmail())
                .userNm(signUpReqDTO.getUserNm())
                .nickNm(signUpReqDTO.getNickNm())
                .build();

        commonUserService.ChkNickNameIsAlreadyExistsThrow(userInfo.getNickNm());
        commonUserService.ChkTelIsAlreadyExistsThrow(userInfo.getTel());
        commonUserService.ChkEmailIsAlreadyExistsThrow(userInfo.getEmail());

        if (ConstExistsCode.EXISTS == signUpRepository.findAlreadySignUpInfo(userInfo)) {
            throw new BaseException(ErrorMsg.SIGNUP_FAIL.getMsg());
        }

        return signUpRepository.save(userInfo).getIdx();
    }
}
