package com.teddybear.reswiki.review.service;

import com.teddybear.reswiki.restaurant.dto.RestaurantDto;
import com.teddybear.reswiki.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getNewReviewList(String id);

    List<ReviewDto> getReviewList(RestaurantDto r);
}
