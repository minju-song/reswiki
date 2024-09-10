package com.teddybear.reswiki.restaurant.service;

import com.teddybear.reswiki.restaurant.dto.RestaurantDto;
import com.teddybear.reswiki.restaurant.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    // 페이지 별 5개의 가게 가져오는 메소드
    List<RestaurantDto> getNewList();

    // 가게 등록
    Restaurant addRestaurant(RestaurantDto restaurantDto);
}
