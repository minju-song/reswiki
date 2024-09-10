package com.teddybear.reswiki.restaurant.web;

import com.teddybear.reswiki.restaurant.dto.RestaurantDto;
import com.teddybear.reswiki.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/getNewList")
    public List<RestaurantDto> getNewList() {

        return restaurantService.getNewList();
    }

    @GetMapping("/testAdd")
    public void testAdd() {
        RestaurantDto dto = RestaurantDto.builder()
                .restaurantName("테스트식당2")
                .restaurantTel("051-111-1111")
                .restaurantImg("맛깔정.jpeg")
                .restaurantAddr1("부산광역시 해운대구")
                .build();

        restaurantService.addRestaurant(dto);
    }
}
