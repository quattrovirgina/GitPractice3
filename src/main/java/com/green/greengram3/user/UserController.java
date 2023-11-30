package com.green.greengram3.user;


import com.green.greengram3.common.ResVo;
import com.green.greengram3.user.model.UserSigninDto;
import com.green.greengram3.user.model.UserSigninVo;
import com.green.greengram3.user.model.UserSignupDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 콘트롤러의 핵1
@RequiredArgsConstructor // 콘트롤러의 핵2
@RequestMapping("/api/user")
@Slf4j // log.info 쓰려면 이거 있어야할것
public class UserController {
    private final UserService service;

    @PostMapping("/signup")
    public ResVo postSignup(@RequestBody UserSignupDto dto) {
        log.info("dto: {}", dto);

        return service.signup(dto);
    }

    @PostMapping("/signin")
    @Operation(summary = "테스트", description = "아이디 및 비번을 활용한 인증처리")
    public UserSigninVo postSignin(@RequestBody UserSigninDto dto) {
            log.info("dto: {}", dto);
            return service.signin(dto);
    }
    // pk, 이름, 프로필사진
}

