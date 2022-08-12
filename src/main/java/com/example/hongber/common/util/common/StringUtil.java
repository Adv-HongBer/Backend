package com.example.hongber.common.util.common;

import com.example.hongber.common.exception.ErrorMsg;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isHangulChk(String str) {
        if (str == null) {
            return false;
        }

        return Pattern.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*", str);
    }

    public static String eliHyphen(String str) {
        return str.replace("-", "");
    }

    public static List<String> divideTelNo(String telNo) {
        List<String> returnStr = new ArrayList<>();

        if (! "0".equals(telNo.substring(0, 1))) {
            throw new RuntimeException(ErrorMsg.WRONG_TEL_NO.getMsg());
        }

        telNo = eliHyphen(telNo);
        String telDiv = telNo.substring(1, 2);

        if (telNo.length() == 9) {
            // 전화번호가 00-000-0000 인 케이스
            returnStr.add(telNo.substring(0, 2));
            returnStr.add(telNo.substring(2, 5));
            returnStr.add(telNo.substring(5, 9));
        } else if (telNo.length() == 10) {
            if ("2".equals(telDiv)) {
                // 전화번호가 00-0000-0000 인 케이스
                returnStr.add(telNo.substring(0, 2));
                returnStr.add(telNo.substring(2, 6));
                returnStr.add(telNo.substring(6, 10));
            } else {
                // 전화번호가 000-000-0000 인 케이스
                returnStr.add(telNo.substring(0, 3));
                returnStr.add(telNo.substring(3, 6));
                returnStr.add(telNo.substring(6, 10));
            }
        } else if (telNo.length() == 11) {
            // 전화번호가 000-0000-0000 인 케이스
            returnStr.add(telNo.substring(0, 3));
            returnStr.add(telNo.substring(3, 7));
            returnStr.add(telNo.substring(7, 11));
        } else if (telNo.length() == 12) {
            if ("5".equals(telDiv)) {
                // 전화번호가 0000-0000-0000 인 케이스(안심번호)
                returnStr.add(telNo.substring(0, 4));
                returnStr.add(telNo.substring(4, 8));
                returnStr.add(telNo.substring(8, 12));
            } else {
                throw new RuntimeException(ErrorMsg.WRONG_TEL_NO.getMsg());
            }
        } else {
            throw new RuntimeException(ErrorMsg.WRONG_TEL_NO.getMsg());
        }

        return returnStr;
    }

    public static String divideTelNo(String telNo, int position) {
        List<String> returnStr = new ArrayList<>();

        if (! "0".equals(telNo.substring(0, 1))) {
            throw new RuntimeException(ErrorMsg.WRONG_TEL_NO.getMsg());
        }

        if (position >= 3 || position < 0) {
            throw new RuntimeException(ErrorMsg.WRONG_PARAMETER.getMsg());
        }

        telNo = eliHyphen(telNo);
        String telDiv = telNo.substring(1, 2);

        if (telNo.length() == 9) {
            // 전화번호가 00-000-0000 인 케이스
            returnStr.add(telNo.substring(0, 2));
            returnStr.add(telNo.substring(2, 5));
            returnStr.add(telNo.substring(5, 9));
        } else if (telNo.length() == 10) {
            if ("2".equals(telDiv)) {
                // 전화번호가 00-0000-0000 인 케이스
                returnStr.add(telNo.substring(0, 2));
                returnStr.add(telNo.substring(2, 6));
                returnStr.add(telNo.substring(6, 10));
            } else {
                // 전화번호가 000-000-0000 인 케이스
                returnStr.add(telNo.substring(0, 3));
                returnStr.add(telNo.substring(3, 6));
                returnStr.add(telNo.substring(6, 10));
            }
        } else if (telNo.length() == 11) {
            // 전화번호가 000-0000-0000 인 케이스
            returnStr.add(telNo.substring(0, 3));
            returnStr.add(telNo.substring(3, 7));
            returnStr.add(telNo.substring(7, 11));
        } else if (telNo.length() == 12) {
            if ("5".equals(telDiv)) {
                // 전화번호가 0000-0000-0000 인 케이스(안심번호)
                returnStr.add(telNo.substring(0, 4));
                returnStr.add(telNo.substring(4, 8));
                returnStr.add(telNo.substring(8, 12));
            } else {
                throw new RuntimeException(ErrorMsg.WRONG_TEL_NO.getMsg());
            }
        } else {
            throw new RuntimeException(ErrorMsg.WRONG_TEL_NO.getMsg());
        }

        return returnStr.get(position);
    }

    public static String trim(String str) {
        if (str == null) {
            return null;
        }

        return str.trim();
    }

    public static String arrayListToJsonString(List<?> objList) {
        Gson gson = new Gson();
        return gson.toJson(objList);
    }
}
