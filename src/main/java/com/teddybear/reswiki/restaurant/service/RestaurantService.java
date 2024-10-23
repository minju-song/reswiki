package com.teddybear.reswiki.restaurant.service;

import com.teddybear.reswiki.restaurant.dto.RestaurantDto;
import com.teddybear.reswiki.restaurant.dto.RestaurantResponse;
import com.teddybear.reswiki.restaurant.entity.Restaurant;

public interface RestaurantService {

    // 페이지 별 5개의 가게 가져오는 메소드
    RestaurantResponse.GetNewList getNewList();

    // 가게 등록
    Restaurant addRestaurant(RestaurantDto restaurantDto);

    // 가게 정보
    RestaurantResponse.GetRestaurant getRestaurant(String id);

    // 가게 검색
    RestaurantResponse.Search search(String keyword, int page, int size);
}
