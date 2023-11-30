package com.green.greengram3.user;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.user.model.UserSigninDto;
import com.green.greengram3.user.model.UserSigninVo;
import com.green.greengram3.user.model.UserSignupDto;
import com.green.greengram3.user.model.UserSignupProcDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserMapper mapper;

    public ResVo signup(UserSignupDto dto) {
        String str = dto.getUpw();// postman 및 swagger에서 비번을 str에 저장하고
        String salt = BCrypt.gensalt(); // gensalt()는 암호화된 값을 salt라는 문자열 변수에 저장한다
        String hashcode = BCrypt.hashpw(str, salt);

        UserSignupProcDto pDto = UserSignupProcDto.builder()
                .uid(dto.getUid())
                .upw(hashcode)
                .nm(dto.getNm())
                .pic(dto.getPic())
                .build();
        /* UserSignupProcDto pDto = new UserSignupProcDto();
           pDto.setUid(dto.getUid());
           pDto.setUpw(hashcode);
           pDto.setNm(dto.getNm());
           pDto.setPic(dto.getPic());
        */

        log.info("before - pDto.iuser : {}", pDto.getIuser());
        int affectedRows = mapper.insUser(pDto);
        log.info("after - pDto.iuser : {}", pDto.getIuser());
        // 아니면 log.info("(중략).. : {}", affectedRows);
        return new ResVo(pDto.getIuser()); //회원 가입한 iuserpk값이 리턴
    }

    public UserSigninVo signin(UserSigninDto dto) {
        String savedpw = mapper.selUserById(dto.getUid());
        UserSigninVo vo = new UserSigninVo();

        if(savedpw == null) {
            vo.setResult(2);
            return vo;
        }

        if(!BCrypt.checkpw(dto.getUpw(), savedpw)) {
            vo.setResult(3);
            return vo;
        }
        vo = mapper.selUserSignin(dto);
        vo.setResult(1);
        return vo;
    }
}
