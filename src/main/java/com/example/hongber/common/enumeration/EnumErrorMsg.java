package com.example.hongber.common.enumeration;

import lombok.Getter;

@Getter
public enum EnumErrorMsg {
    WRONG_PARAMETER("잘못된 파라미터", "파라미터를 확인해주세요."),
    WRONG_TEL_NO("잘못된 형식의 전화번호", "전화번호 형식을 확인해주세요."),
    ;

    private final String logMsg;
    private final String msg;

    EnumErrorMsg(String logMsg, String msg) {
        this.logMsg = logMsg;
        this.msg = msg;
    }
}
