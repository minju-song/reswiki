package com.teddybear.reswiki.restaurant.web;

import com.teddybear.reswiki.restaurant.dto.RestaurantResponse;
import com.teddybear.reswiki.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // 메인홈
    @GetMapping
    public RestaurantResponse.Home getRestaurants(@RequestParam("size") int size,
                                               @RequestParam("page") int page){
        return restaurantService.home(size,page);
    }


    // 식당 검색
    @GetMapping("/search")
    public RestaurantResponse.Search getSearchRestaurants(@RequestParam("keyword") String keyword,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("size") int size) {
       return restaurantService.search(keyword, page, size);

    }

    // 식당 상세페이지
    @GetMapping("/{id}")
    public RestaurantResponse.RestaurantDto getRestaurant(@PathVariable("id") String id) {

        return restaurantService.getRestaurant(id);
    }

}
