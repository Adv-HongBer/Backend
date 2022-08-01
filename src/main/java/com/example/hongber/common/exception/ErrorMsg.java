package com.example.hongber.common.exception;

import lombok.Getter;

@Getter
public enum ErrorMsg {
    WRONG_PARAMETER("잘못된 파라미터", "파라미터를 확인해주세요."),
    WRONG_TEL_NO("잘못된 형식의 전화번호", "전화번호 형식을 확인해주세요."),
    ENCRYPT_FAIL("암호화 실패", "데이터 암호화 중 오류가 발생하였습니다."),
    DECRYPT_FAIL("복호화 실패", "데이터 복호화 중 오류가 발생하였습니다."),
    ;

    private final String logMsg;
    private final String msg;

    ErrorMsg(String logMsg, String msg) {
        this.logMsg = logMsg;
        this.msg = msg;
    }
}
