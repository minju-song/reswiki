package com.teddybear.reswiki.review.dto;

import com.teddybear.reswiki.review.entity.Review;
import com.teddybear.reswiki.review.entity.ReviewItem;

import java.util.Date;

public class ReviewResponse {

    public record ReviewDto(
            int reviewId,
//            MemberDto writerId,
            ReviewItem reviewItem,
            String reviewContents,
            Date reviewDate
    ) {
        public static ReviewDto from(Review r) {
            return new ReviewDto(
                    r.getReviewId(),
                    r.getReviewItem(),
                    r.getReviewContents(),
                    r.getReviewDate()
            );
        }
    }
}
