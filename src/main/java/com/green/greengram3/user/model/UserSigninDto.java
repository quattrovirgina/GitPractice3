package com.green.greengram3.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder

public class UserSigninDto {
    private String uid;
    private String upw;
}
