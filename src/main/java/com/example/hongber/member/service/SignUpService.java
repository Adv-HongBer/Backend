package com.example.hongber.member.service;

import com.example.hongber.common.constant.ConstExistsCode;
import com.example.hongber.common.exception.BaseException;
import com.example.hongber.common.exception.msg.ErrorMsg;
import com.example.hongber.member.dto.SignUpReqDTO;
import com.example.hongber.member.entity.MemberET;
import com.example.hongber.member.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {
    private final SignUpRepository signUpRepository;
    private final CommonMemberService commonMemberService;

    @Transactional
    public Long SignUp(SignUpReqDTO signUpReqDTO) {
        MemberET memberInfo = MemberET.builder()
                .memberId(signUpReqDTO.getMemberId())
                .pass(signUpReqDTO.getPass())
                .tel(signUpReqDTO.getTel())
                .email(signUpReqDTO.getEmail())
                .memberNm(signUpReqDTO.getMemberNm())
                .nickNm(signUpReqDTO.getNickNm())
                .build();

        commonMemberService.ChkNickNameIsAlreadyExistsThrow(memberInfo.getNickNm());
        commonMemberService.ChkTelIsAlreadyExistsThrow(memberInfo.getTel());
        commonMemberService.ChkEmailIsAlreadyExistsThrow(memberInfo.getEmail());

        if (ConstExistsCode.EXISTS == signUpRepository.findAlreadySignUpInfo(memberInfo)) {
            throw new BaseException(ErrorMsg.SIGNUP_FAIL.getMsg());
        }

        return signUpRepository.save(memberInfo).getIdx();
    }
}
