package com.teddybear.reswiki.restaurant.service;

import com.teddybear.reswiki.restaurant.dto.RestaurantResponse;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // 메인홈
    @Override
    public RestaurantResponse.Home home(int size, int page) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Restaurant> restaurants = restaurantRepository.findAllByOrderByRestaurantIdAsc(pageable);

        return RestaurantResponse.Home.of(restaurants);
    }

    // 이름으로 가게 검색
    @Override
    public RestaurantResponse.Search search(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Restaurant> restaurantPage = restaurantRepository.findByRestaurantNameContaining(keyword, pageable);

        return RestaurantResponse.Search.from(restaurantPage);
    }


    // 가게 정보 가져오기
    @Override
    public RestaurantResponse.RestaurantDto getRestaurant(String id) {
        // 식당을 ID로 조회
        Restaurant restaurant = restaurantRepository.findByRestaurantId(id)
                .orElseThrow(() -> new NoSuchElementException("해당 가게가 없습니다."));

        // GetRestaurant DTO로 변환하여 반환
        return RestaurantResponse.RestaurantDto.from(restaurant);
    }




}
