package com.example.hongber.user.dto;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO extends BaseDTO {
    private String userId;
    private String pass;
    private String tel;
    private String email;
    private String userNm;
    private String nickNm;
    private Long userTypeIdx;
    private Long userStatusIdx;
}
