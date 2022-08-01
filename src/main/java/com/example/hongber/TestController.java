package com.example.hongber;

import com.example.hongber.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class TestController {
    private final TestService testService;

    @PostMapping("test")
    public void SignIn(@RequestBody UserDTO userDTO) {
        log.info("userDTO ==> {}", userDTO);
        testService.save(userDTO);
    }
}
