package com.teddybear.reswiki.review.service;

import com.teddybear.reswiki.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getReviewList(String id);
}
