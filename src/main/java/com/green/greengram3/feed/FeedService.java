package com.green.greengram3.feed;

import com.green.greengram3.common.Const;
import com.green.greengram3.common.ResVo;
import com.green.greengram3.feed.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 핵1
@Slf4j

@RequiredArgsConstructor
// 핵2
public class FeedService {
   private final FeedMapper mapper;
   private final FeedPicsMapper Picsmapper;
   private final FeedFavMapper Favmapper;
   private final FeedCommentMapper Commentmapper;
   public ResVo postFeed(FeedInsDto dto) {

      int infectedRows = mapper.insFeed(dto);

      int feedPicsAffectedRows = Picsmapper.insFeedPics(dto);

      log.info("FeedPicsAffectedRows: {}", feedPicsAffectedRows);

      return new ResVo(dto.getIfeed());
   }

   public List<FeedSelVo> getFeedAll(FeedSelDto dto) {
      log.info("dto: {}", dto);
      List<FeedSelVo> list = mapper.selFeedAll(dto);
      log.info("beforeresult: {}", list);
      FeedCommentSelDto fcDto = new FeedCommentSelDto();
      fcDto.setStartIdx(0);
      fcDto.setRowCount(4);
      // 여기서부터
      for (FeedSelVo fe: list) { // 향상된포문: FeedSelVo를 담고있는 fe는 위의 list에 대하여
         fe.setPics(Picsmapper.SelFeedPics(fe.getIfeed()));
         // fe.setPics(5번방의 사진데이터);
         // list에 담긴값들을
         // fe에 담아주고 fe 내에서 fe내에서 가져온 ifeed를 파라미터로 하여
         // selFeedPics에 넣어주고
         // fe 내에서 String 값의 데이터들을
         // FeedSelVo에 지정된 상태로 세팅한다
         fcDto.setIfeed(fe.getIfeed());
         // fcDto 내에서는 fe내에 있는 Ifeed
         List<FeedCommentSelVo> comments = Commentmapper.selFeedCommentAll(fcDto);
         // FeedCommentSelVo를 담고있는 List형태의 comments는
         fe.setComments(comments);
         if(comments.size() == 4) {
            fe.setIsMoreComment(1);
            comments.remove(comments.size() - 1);
         }
         fe.setComments(comments);
         // 여기까지가 페이징 기법
      }
      log.info("afterresult: {}", list);
      return list;
   }

   public ResVo toggleFeedFav(FeedFavDto dto) {
      //resvo의 result 값은 삭제했을시 0이 리턴되도록 할것
      // 등록 했을시 1이 리턴되도록 할것
      int result = Favmapper.DelFeedFav(dto); // insFeedFav(dto)를 적을시 sql에서 중복되는 값이 있을수도 있기 때문에
      // sql에서 에러가 나므로 DelFeedFav(dto)를 먼저 선언할것

      if(result == 0) { // 영향받은 행(row)의 수가 0일때
         Favmapper.insFeedFav(dto); // insFeedFav(dto)를 통해 result에 1을 증가시켜
         return new ResVo(Const.FEED_FAV_INS); // FEED_FAV_INS = 1
         // return new ResVo(1);

      }

      return new ResVo(Const.FEED_FAV_DEL); // FEED_FAV_DEL = 0
      // return new ResVo(0);
   }

   public ResVo DelFeed(FeedDelDto dto) {
      int result = mapper.selectiuser(dto.getIfeed());
      if(result == dto.getIuser()) {
         Commentmapper.delFeedComment(dto);
         Favmapper.DelFav(dto);
         Picsmapper.DelFeedPics(dto);
         mapper.DelFeed(dto);

         return new ResVo(Const.SUCCESS);
      }

      return new ResVo(Const.FAILED);
   }

   // feedfav인 경우 0과 1의 이진법의 toggle 이므로 삭제를 우선으로 해볼것
   // int AffectedRows = picsMapper.delFeedPics(dto);
   // if(picsAffectedRows == 0) {
   //
   //    return new ResVo(0);
   //    int favAffectedRows = favMapper.delFeedFavAll(dto);
   //    int CommentAffectedRows = CommentMapper.delFeedComment(dto);
   // }
}
