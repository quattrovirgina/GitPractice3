package com.green.greengram3.feed;

import com.green.greengram3.feed.model.FeedSelDto;
import com.green.greengram3.feed.model.FeedSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface FeedSelMapper {
    List<FeedSelVo> insFeedSel(FeedSelDto dto);
}
