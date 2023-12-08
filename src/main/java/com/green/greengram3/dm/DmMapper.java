package com.green.greengram3.dm;


import com.green.greengram3.dm.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DmMapper {
    List<DmMsgSelVo> selDmMsgAll(DmSelDto dto);

    // ------------------------------------- t_dm_user
    int insDm(DmUserInsDto dto);
    // ------------------------------------- t_dm_msg
    int insDmMsg(DmMsgInsDto dto);
    int insDmuser(DmUserInsDto dto);
    // --------------------------------------
    List<DmSelVo> selDmAll(DmSelDto dto);
    // --------------------------------------\
    List <DmMsgSelVo> selDmMsgAll(DmMsgSelDto dto);
}