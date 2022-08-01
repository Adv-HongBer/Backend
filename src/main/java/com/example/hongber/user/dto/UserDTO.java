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
    @Encrypt
    private String userId;
    @Encrypt
    private String pass;
    @Encrypt
    private String tel;
    @Encrypt
    private String email;
    @Encrypt
    private String userNm;
    @Encrypt
    private String nickNm;
    private Long userTypeIdx;
    private Long userStatusIdx;
}
