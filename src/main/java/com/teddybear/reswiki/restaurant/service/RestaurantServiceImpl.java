package com.teddybear.reswiki.restaurant.service;

import com.teddybear.reswiki.restaurant.dto.RestaurantDto;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{

//    @Autowired
//    private RestaurantRepository restaurantRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // 최신 리뷰 등록된 5개의 가게 가져오는 메소드
    @Override
    public List<RestaurantDto> getNewList() {
        List<Restaurant> restaurants = restaurantRepository.findTop5ByOrderByRestaurantUpdateDesc();

        List<RestaurantDto> restaurantDtos = restaurants.stream()
                .map(restaurant -> {
                    RestaurantDto dto = RestaurantDto.toDto(restaurant);
                    return dto;
                })
                .collect(Collectors.toList());
        return restaurantDtos;
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
        restaurantRepository.findByRestaurantName(restaurant.getRestaurantName())
                .ifPresent(r -> {
                    throw new IllegalStateException("이미 존재하는 가게입니다.");
                });
    }


}
