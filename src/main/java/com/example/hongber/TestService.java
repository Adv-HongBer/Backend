package com.example.hongber;

import com.example.hongber.user.dto.UserDTO;
import com.example.hongber.user.entity.UserET;
import com.example.hongber.user.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TestService {
    private final SignUpRepository signUpRepository;

    public void save(UserDTO userDTO) {
        UserET tmpUserInfo = new UserET();
        tmpUserInfo.setUserId(userDTO.getUserId());
        tmpUserInfo.setPass(userDTO.getPass());
        tmpUserInfo.setTel(userDTO.getTel());
        tmpUserInfo.setEmail(userDTO.getEmail());
        tmpUserInfo.setUserNm(userDTO.getUserNm());
        tmpUserInfo.setNickNm(userDTO.getNickNm());
        tmpUserInfo.setUserTypeIdx(userDTO.getUserTypeIdx());
        tmpUserInfo.setUserStatusIdx(userDTO.getUserStatusIdx());

        signUpRepository.save(tmpUserInfo);
    }
}
