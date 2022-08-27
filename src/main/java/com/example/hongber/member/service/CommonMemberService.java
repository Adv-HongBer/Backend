package com.example.hongber.member.service;

import com.example.hongber.common.constant.ConstExistsCode;
import com.example.hongber.common.exception.BaseException;
import com.example.hongber.common.exception.msg.ErrorMsg;
import com.example.hongber.common.util.encrypt.AESEncryptor;
import com.example.hongber.member.repository.CommonMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonMemberService {
    private Boolean flag = false;
    private final CommonMemberRepository commonMemberRepository;

    // non throw
    public Boolean ChkNickNameIsAlreadyExists(String nickName) {
        return this.ChkNickNameIsAlreadyExists(nickName, false);
    }
    public Boolean ChkTelIsAlreadyExists(String nickName) {
        return this.ChkTelIsAlreadyExists(nickName, false);
    }
    public Boolean ChkEmailIsAlreadyExists(String nickName) {
        return this.ChkEmailIsAlreadyExists(nickName, false);
    }
    // throw
    public void ChkNickNameIsAlreadyExistsThrow(String nickName) {
        this.ChkNickNameIsAlreadyExists(nickName, true);
    }
    public void ChkTelIsAlreadyExistsThrow(String nickName) {
        this.ChkTelIsAlreadyExists(nickName, true);
    }
    public void ChkEmailIsAlreadyExistsThrow(String nickName) {
        this.ChkEmailIsAlreadyExists(nickName, true);
    }

    public Boolean ChkNickNameIsAlreadyExists(String nickName, Boolean throwAble) {
        Integer result = commonMemberRepository.chkNickNameIsAlreadyExists(AESEncryptor.encAes128E(nickName));

        if (ConstExistsCode.EXISTS == result) {
            if (throwAble) {
                throw new BaseException(ErrorMsg.ALREADY_EXISTS_NICKNAME.getMsg());
            }

            flag = true;
        }

        return flag;
    }

    public Boolean ChkTelIsAlreadyExists(String tel, Boolean throwAble) {
        Integer result = commonMemberRepository.chkTelIsAlreadyExists(AESEncryptor.encAes128E(tel));

        if (ConstExistsCode.EXISTS == result) {
            if (throwAble) {
                throw new BaseException(ErrorMsg.ALREADY_EXISTS_TEL.getMsg());
            }

            flag = true;
        }

        return flag;
    }

    public Boolean ChkEmailIsAlreadyExists(String email, Boolean throwAble) {
        Integer result = commonMemberRepository.chkEmailIsAlreadyExists(AESEncryptor.encAes128E(email));

        if (ConstExistsCode.EXISTS == result) {
            if (throwAble) {
                throw new BaseException(ErrorMsg.ALREADY_EXISTS_EMAIL.getMsg());
            }

            flag = true;
        }

        return flag;
    }
}
