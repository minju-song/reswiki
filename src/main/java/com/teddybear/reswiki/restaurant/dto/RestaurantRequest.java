package com.teddybear.reswiki.restaurant.dto;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import jakarta.validation.constraints.NotEmpty;

public class RestaurantRequest {
    public record JoinRestaurantDto(
            @NotEmpty
            String restaurantId,
            @NotEmpty
            String restaurantName,
            String restaurantTel,
            String restaurantImg,
            String restaurantAddr1,
            String restaurantAddr2
    ) {
        public Restaurant toRestaurant() {
            return Restaurant.builder()
                    .restaurantId(restaurantId)
                    .restaurantName(restaurantName)
                    .restaurantTel(restaurantTel)
                    .restaurantImg(restaurantImg)
                    .restaurantAddr1(restaurantAddr1)
                    .restaurantAddr2(restaurantAddr2)
                    .build();
        }
    }

}
