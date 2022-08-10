package com.example.hongber.user.controller;

import com.example.hongber.user.dto.SignInReqDTO;
import com.example.hongber.user.entity.UserET;
import com.example.hongber.user.service.SignInService;
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
public class SignInController {
    private final SignInService signInService;

    @PostMapping("signIn")
    public UserET SignIn(@Validated @RequestBody SignInReqDTO signInReqDTO) {
        return signInService.signIn(signInReqDTO);
    }
}
