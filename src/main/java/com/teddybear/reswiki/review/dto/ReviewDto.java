package com.teddybear.reswiki.review.dto;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.review.entity.IsNewest;
import com.teddybear.reswiki.review.entity.Review;
import com.teddybear.reswiki.review.entity.ReviewItem;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    // 리뷰 아이디
    private int reviewId;

    // 작성자 아이디
    private String writerId;

    // 가게 아이디
    private Restaurant restaurantId;

    // 리뷰 항목
    private ReviewItem reviewItem;

    // 리뷰 내용
    private String reviewContents;

    // 최신 리뷰인지 Y/N
    private IsNewest isNewest;

    // 리뷰 등록 날짜
    private Date reviewDate;

    public static ReviewDto toDto(Review r) {
        return ReviewDto.builder()
                .reviewId(r.getReviewId())
                .writerId(r.getWriterId().getMemberId())
                .restaurantId(r.getRestaurantId())
                .reviewItem(r.getReviewItem())
                .reviewContents(r.getReviewContents())
                .reviewDate(r.getReviewDate())
                .build();
    }
}
