package com.teddybear.reswiki.review.dto;

import com.teddybear.reswiki.member.dto.MemberDto;
import com.teddybear.reswiki.review.entity.Review;
import com.teddybear.reswiki.review.entity.ReviewItem;

import java.util.Date;

public class ReviewResponse {

    public record GetReview(
            int reviewId,
            MemberDto writerId,
            ReviewItem reviewItem,
            String reviewContents,
            Date reviewDate
    ) {
        public GetReview(Review r) {
            this(
                    r.getReviewId(),
                    MemberDto.toDto(r.getWriterId()),
                    r.getReviewItem(),
                    r.getReviewContents(),
                    r.getReviewDate()
            );
        }
    }
}
