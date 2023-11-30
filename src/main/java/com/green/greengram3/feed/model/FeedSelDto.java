package com.green.greengram3.feed.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class FeedSelDto {
    private int startIdx;
    private int finishIdx;
}
