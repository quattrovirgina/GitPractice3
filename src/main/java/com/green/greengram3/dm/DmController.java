package com.green.greengram3.dm;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.dm.model.DmMsgInsDto;
import com.green.greengram3.dm.model.DmSelDto;
import com.green.greengram3.dm.model.DmMsgSelVo;
import com.green.greengram3.dm.model.DmSelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/dm")

public class DmController {

    private final DmService service;

    @GetMapping // 불 꺼져있다면 이거 빼먹은거라고 봐야함
    public List<DmSelVo> getDmAll(DmSelDto dto) {
        return service.getDmAll(dto);
    }
    @GetMapping("/msg")
    public List<DmMsgSelVo> getMsgAll(DmSelDto dto) {
        log.info("dto : {}", dto);
        return service.getMsgAll(dto);

    }
    @PostMapping("/msg")
    public ResVo postDmMsg(@RequestBody DmMsgInsDto dto) {
        return service.postDmMsg(dto);
    }
}
