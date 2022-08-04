package com.example.hongber.user.controller;

import com.example.hongber.user.entity.UserET;
import com.example.hongber.user.service.TestService;
import com.example.hongber.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class TestController {
    private final TestService testService;

    @PostMapping("test")
    public void SignIn(@RequestBody UserDTO userDTO) {
        testService.saveTest(userDTO);
    }

    @GetMapping("test")
    public List<UserET> findTest(@ModelAttribute UserDTO userDTO) {
        return testService.findTest(userDTO);
    }
}
