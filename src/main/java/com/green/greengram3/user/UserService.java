package com.green.greengram3.user;

import com.green.greengram3.common.Const;
import com.green.greengram3.common.ResVo;
import com.green.greengram3.user.model.*;
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
        UserSelDto vo = new UserSelDto();
        vo.setUid(dto.getUid());

        UserEntity entity = mapper.selUser(vo);
        if (entity == null) {
            return UserSigninVo.builder().result(Const.LOGIN_NO_UID).build();
        } else if (!BCrypt.checkpw(dto.getUpw(), entity.getUpw())) {
            return UserSigninVo.builder().result(Const.LOGIN_DIFF_UPW).build();
        }

        return UserSigninVo.builder()
                .result(Const.SUCCESS)
                .iuser(entity.getIuser())
                .nm(entity.getNm())
                .pic(entity.getPic())
                .build();
    }

    public UserInfoVo getUserInfo(UserInfoSelDto dto) {
        return mapper.SelUserInfo(dto);
    }

    public ResVo toggleFollow(UserFollowDto dto) {
        int delAffectedRows = mapper.DelFollow(dto);
        if(delAffectedRows == 1) {
            return new ResVo(Const.FAILED);
        }
        int insAffectedRows = mapper.InsFollow(dto);
        return new ResVo(Const.SUCCESS);
    }

}