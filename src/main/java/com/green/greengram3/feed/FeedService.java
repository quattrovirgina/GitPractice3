package com.green.greengram3.feed;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.feed.model.FeedInsDto;
import com.green.greengram3.feed.model.FeedInsProcDto;
import com.green.greengram3.feed.model.FeedSelDto;
import com.green.greengram3.feed.model.FeedSelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedService {
   private final FeedMapper mapper;
   private final FeedPicsMapper Picsmapper;
   private final FeedSelMapper Selmapper;

   public ResVo postFeed(FeedInsDto dto) {
      FeedInsProcDto save = FeedInsProcDto.builder()
              .iuser(dto.getIuser())
              .contents(dto.getContents())
              .location(dto.getLocation())
              .pics(dto.getPics())
              .build();
      int infectedRows = mapper.insFeed(save);

      if(infectedRows == 0 || dto.getContents() == null) {
         return new ResVo(3);
      }
      int feedPicsAffectedRows = Picsmapper.insFeedPics(save);

      return new ResVo(save.getIfeed());
   }

   public List<FeedSelVo> gfeed(FeedSelDto dto) {

      return Selmapper.insFeedSel(dto);
   }
}
