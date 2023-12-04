package com.green.greengram3.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.greengram3.common.Const;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class FeedSelDto {
    private int page;
    private int loginedIuser;

    private int targetIuser;

    private int isFavList; // 안보내면 default 값인 0, 보내면 1

    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int finishIdx = Const.RCPP;

    public void setPage(int page) {
        this.startIdx = (page - 1) * finishIdx;
    }
}

//JsonIgnore 어노테이션으로 박스갈이를 무시할수 있음