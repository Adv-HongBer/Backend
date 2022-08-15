package com.example.hongber.common.exception.msg;

import lombok.Getter;

@Getter
public enum ErrorMsg {
    WRONG_PARAMETER("잘못된 파라미터", "입력값을 확인해주세요."),
    WRONG_TEL_NO("잘못된 형식의 전화번호", "전화번호 형식을 확인해주세요."),
    ENCRYPT_FAIL("암호화 실패", "데이터 암호화 중 오류가 발생하였습니다."),
    DECRYPT_FAIL("복호화 실패", "데이터 복호화 중 오류가 발생하였습니다."),
    SIGNIN_FAIL("로그인 실패", "로그인 중 문제가 발생하였습니다. 해당 문제가 지속된다면 관리자에게 문의주시길 바랍니다."),
    SIGNUP_FAIL("회원가입 실패", "회원가입 중 문제가 발생하였습니다. 해당 문제가 지속된다면 관리자에게 문의주시길 바랍니다."),
    ALREADY_EXISTS_NICKNAME("이미 존재하는 닉네임", "해당 닉네임은 누군가 사용중입니다."),
    ALREADY_EXISTS_TEL("이미 존재하는 전화번호", "해당 전화번호로 가입한 사용자가 존재합니다."),
    ALREADY_EXISTS_EMAIL("이미 존재하는 이메일", "해당 이메일로 가입한 사용자가 존재합니다."),
    ;

    private final String logMsg;
    private final String msg;

    ErrorMsg(String logMsg, String msg) {
        this.logMsg = logMsg;
        this.msg = msg;
    }
}
