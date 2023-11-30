package com.green.greengram3.user;

import com.green.greengram3.user.model.UserSigninDto;
import com.green.greengram3.user.model.UserSigninVo;
import com.green.greengram3.user.model.UserSignupDto;
import com.green.greengram3.user.model.UserSignupProcDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignupProcDto pDto);

    String selUserById(String uid);

    UserSigninVo selUserSignin(UserSigninDto dto);
}
