package com.green.greengram3.feed;

import com.green.greengram3.feed.model.FeedCommentInsDto;
import com.green.greengram3.feed.model.FeedCommentSelDto;
import com.green.greengram3.feed.model.FeedCommentSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int insFeedComment(FeedCommentInsDto dto);
    List<FeedCommentSelVo> selFeedCommentAll(FeedCommentSelDto dto);
    // 만약 all이라고 써있다면 List<> 붙여놓을것
}

// insFeedComment
// select 에서는 vo와 dto가 관여. sql 쿼리 실행시 vo에다 저장되고, dto는 #{}가 채워져있는 값들을 저장
