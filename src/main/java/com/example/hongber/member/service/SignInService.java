package com.example.hongber.member.service;

import com.example.hongber.common.exception.BaseException;
import com.example.hongber.common.exception.msg.ErrorMsg;
import com.example.hongber.member.dto.SignInReqDTO;
import com.example.hongber.member.entity.MemberET;
import com.example.hongber.member.repository.SignInRepository;
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
    public MemberET signIn(SignInReqDTO signInReqDTO) {
        MemberET memberInfo = MemberET.builder()
                .memberId(signInReqDTO.getMemberId())
                .pass(signInReqDTO.getPass())
                .build();

        try {
            return signInRepository.findByMemberIdAndPass(memberInfo);
        } catch (Exception e) {
            throw new BaseException(ErrorMsg.SIGNUP_FAIL.getMsg());
        }
    }
}
