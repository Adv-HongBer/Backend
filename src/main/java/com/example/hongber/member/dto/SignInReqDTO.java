package com.example.hongber.member.dto;

import com.example.hongber.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class SignInReqDTO extends BaseDTO {
    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()?_~]{8,20}$", message = "아이디는 8~20자 영문 대 소문자, 숫자, 특수문자를 사용해야합니다.")
    private String memberId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{10,20}$", message = "비밀번호는 8~20자 영문 대 소문자, 숫자, 특수문자를 사용해야합니다.")
    private String pass;
}
