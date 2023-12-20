package com.green.greengram3.feed;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.feed.model.FeedInsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({FeedService.class})

class FeedServiceTest {

    @MockBean
    private FeedMapper mapper;

    @MockBean
    private FeedPicsMapper picsMapper;

    @MockBean
    private FeedFavMapper favMapper;

    @MockBean
    private FeedCommentMapper commentMapper;

    @Autowired
    private FeedService service;
    @Test
    void postFeed() {
        when(mapper.insFeed(any())).thenReturn(1);
        // any는 뭔가 주입이 될것. 즉 뭔가 집어넣게되는 상황이올때
        // 1을 리턴하라
        when(picsMapper.insFeedPics(any())).thenReturn(3);
        // picsMapper >> insFeedPics에 무슨 값이 들어온다면 3을 리턴하라

        FeedInsDto dto = new FeedInsDto();
        ResVo vo = service.postFeed(dto);

        assertEquals(dto.getIfeed(), vo.getResult());

        verify(mapper).insFeed(any());
        verify(picsMapper).insFeedPics(any());

        FeedInsDto dto2 = new FeedInsDto();
        dto2.setIfeed(200);
        ResVo vo2 = service.postFeed(dto2);
        assertEquals(dto2.getIfeed(), vo2.getResult());



    }

    @Test
    void gfeed() {
    }

    @Test
    void toggleFeedFav() {
    }

    @Test
    void delFeed() {
    }
}