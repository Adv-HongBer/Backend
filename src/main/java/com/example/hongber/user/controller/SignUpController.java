package com.example.hongber.user.controller;

import com.example.hongber.user.dto.SignUpReqDTO;
import com.example.hongber.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("signUp")
    public Long SignUp(@Validated @RequestBody SignUpReqDTO signUpReqDTO) {
        return signUpService.SignUp(signUpReqDTO);
    }
}
