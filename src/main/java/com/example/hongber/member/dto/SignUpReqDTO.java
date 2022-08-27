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
public class SignUpReqDTO extends BaseDTO {
    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()?_~]{8,20}$", message = "아이디는 8~20자 영문 대 소문자, 숫자, 특수문자를 사용해야합니다.")
    private String memberId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{10,20}$", message = "비밀번호는 8~20자 영문 대 소문자, 숫자, 특수문자를 사용해야합니다.")
    private String pass;

    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(\\d{2,3}\\d{3,4}\\d{4})|(\\d{2,3}-\\d{3,4}-\\d{4})$", message = "입력하신 전화번호는 형식에 맞지 않습니다.")
    private String tel;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "입력하신 이메일은 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "성함은 필수 입력값입니다.")
    @Pattern(regexp = "^[가-힣]{2,}$", message = "성함을 정확히 입력해주세요.")
    private String memberNm;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String nickNm;
}