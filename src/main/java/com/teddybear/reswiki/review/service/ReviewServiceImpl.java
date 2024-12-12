package com.teddybear.reswiki.review.service;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.restaurant.repository.RestaurantRepository;
import com.teddybear.reswiki.review.dto.ReviewDto;
import com.teddybear.reswiki.review.entity.IsNewest;
import com.teddybear.reswiki.review.entity.Review;
import com.teddybear.reswiki.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository= restaurantRepository;
    }

    @Override
    public List<ReviewDto> getNewReviewList(String id) {
        Restaurant r = restaurantRepository.findByRestaurantId(id).get();
        List<Review> reviews = reviewRepository.findByRestaurantIdAndIsNewest(r, IsNewest.Y);

        List<ReviewDto> reviewDtos = reviews.stream()
                .map(review -> {
                    ReviewDto dto = ReviewDto.toDto(review);
                    return dto;
                })
                .collect(Collectors.toList());
        return reviewDtos;
    }

//    @Override
//    public List<ReviewDto> getReviewList(RestaurantDto r) {
//        Restaurant restaurant = Restaurant.toEntity(r);
//
//        List<Review> reviews = reviewRepository.findByRestaurantId(restaurant);
//
//        if (reviews.isEmpty()) {
//            return Collections.emptyList();         }
//
//        List<ReviewDto> reviewDtos = reviews.stream()
//                .map(ReviewDto::toDto)
//                .collect(Collectors.toList());
//
//        return reviewDtos;
//    }
}
