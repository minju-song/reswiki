package com.teddybear.reswiki.restaurant.service;

import com.teddybear.reswiki.restaurant.dto.RestaurantDto;
import com.teddybear.reswiki.restaurant.dto.RestaurantResponse;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.restaurant.repository.RestaurantRepository;
import com.teddybear.reswiki.review.entity.Review;
import com.teddybear.reswiki.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RestaurantServiceImpl implements RestaurantService{

//    @Autowired
//    private RestaurantRepository restaurantRepository;

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    // 최신 리뷰 등록된 5개의 가게 가져오는 메소드
    @Override
    public RestaurantResponse.GetNewList getNewList() {
        List<Restaurant> restaurants = restaurantRepository.findTop5ByOrderByRestaurantUpdateDesc();

        return RestaurantResponse.GetNewList.of(restaurants);
    }

    // 가게 등록
    @Override
    public Restaurant addRestaurant(RestaurantDto dto) {

        Restaurant restaurant = Restaurant.toEntity(dto);

        checkRestaurant(restaurant);

        return restaurantRepository.save(restaurant);
    }

    // 가게 중복 체크 (임의로 일단 이름으로 체크)
    private void checkRestaurant(Restaurant restaurant) {
        restaurantRepository.findByRestaurantId(restaurant.getRestaurantId())
                .ifPresent(r -> {
                    throw new IllegalStateException("이미 존재하는 가게입니다.");
                });
    }


    // 가게 정보 가져오기
    @Override
    public RestaurantResponse.GetRestaurant getRestaurant(String id) {
        // 식당을 ID로 조회
        Restaurant restaurant = restaurantRepository.findByRestaurantId(id)
                .orElseThrow(() -> new NoSuchElementException("해당 가게가 없습니다."));

        // 해당 식당의 리뷰를 조회
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurant);

        // GetRestaurant DTO로 변환하여 반환
        return new RestaurantResponse.GetRestaurant(restaurant, reviews);
    }

    // 이름으로 가게 검색
    @Override
    public RestaurantResponse.Search search(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Restaurant> restaurantPage = restaurantRepository.findByRestaurantNameContaining(keyword, pageable);

        return RestaurantResponse.Search.of(restaurantPage);
    }


}
