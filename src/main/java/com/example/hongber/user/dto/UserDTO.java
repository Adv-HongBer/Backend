package com.example.hongber.user.dto;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.common.dto.BaseDTO;
import com.example.hongber.common.enumeration.EncryptType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO extends BaseDTO {
    @Encrypt(EncryptType.AES128)
    private String userId;
    @Encrypt(EncryptType.AES128)
    private String pass;
    @Encrypt(EncryptType.AES128)
    private String tel;
    @Encrypt(EncryptType.AES128)
    private String email;
    @Encrypt(EncryptType.AES128)
    private String userNm;
    @Encrypt(EncryptType.AES128)
    private String nickNm;
    private Long userTypeIdx;
    private Long userStatusIdx;
}
