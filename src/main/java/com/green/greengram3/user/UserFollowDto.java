package com.green.greengram3.user;

import lombok.Data;

@Data
public class UserFollowDto {
    private int fromIuser;
    private int toIuser;
}
