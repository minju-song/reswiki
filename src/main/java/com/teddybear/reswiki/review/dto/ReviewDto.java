package com.teddybear.reswiki.review.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewDto {

    // 리뷰 아이디
    private int reviewId;

    // 작성자 아이디
    private String writerId;

    // 가게 아이디
    private int restaurantId;

    // 리뷰 항목
    private String reviewItem;

    // 리뷰 내용
    private String reviewContents;

    // 최신 리뷰인지 Y/N
    private char isNewest;

    // 리뷰 등록 날짜
    private Date reviewDate;
}
