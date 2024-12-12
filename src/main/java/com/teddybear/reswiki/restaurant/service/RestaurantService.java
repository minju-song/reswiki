package com.teddybear.reswiki.restaurant.service;

import com.teddybear.reswiki.restaurant.dto.RestaurantResponse;

public interface RestaurantService {

    // 페이지 별 5개의 가게 가져오는 메소드
    RestaurantResponse.Home home(int size, int page);

    // 가게 정보
    RestaurantResponse.RestaurantDto getRestaurant(String id);

    // 가게 검색
    RestaurantResponse.Search search(String keyword, int page, int size);
}
