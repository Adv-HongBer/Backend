package com.example.hongber.member.dto;

import com.example.hongber.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO extends BaseDTO {
    private String memberId;
    private String pass;
    private String tel;
    private String email;
    private String memberNm;
    private String nickNm;
    private Long memberTypeIdx;
    private Long memberStatusIdx;
}
