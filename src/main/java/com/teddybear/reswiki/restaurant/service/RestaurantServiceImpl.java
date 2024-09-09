package com.teddybear.reswiki.restaurant.service;

import com.teddybear.reswiki.restaurant.dto.RestaurantDto;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.restaurant.repository.RestaurantRepository;
import com.teddybear.reswiki.review.entity.IsNewest;
import com.teddybear.reswiki.review.entity.Review;
import com.teddybear.reswiki.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // 최신 리뷰 등록된 5개의 가게 가져오는 메소드
    @Override
    public List<RestaurantDto> getNewList() {
        List<Restaurant> restaurants = restaurantRepository.findTop5ByOrderByRestaurantUpdateDesc();

        List<RestaurantDto> restaurantDtos = restaurants.stream()
                .map(restaurant -> new RestaurantDto(restaurant.getRestaurantId(), restaurant.getRestaurantName(), restaurant.getRestaurantAddr1(), restaurant.getRestaurantUpdate(), restaurant.getRestaurantImg()))
                .collect(Collectors.toList());
        return restaurantDtos;
    }
}
