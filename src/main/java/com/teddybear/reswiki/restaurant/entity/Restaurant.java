package com.teddybear.reswiki.restaurant.entity;

import com.teddybear.reswiki.restaurant.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    // 가게 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int restaurantId;

    // 가게 이름
    @Column(name = "restaurant_name")
    private String restaurantName;

    // 가게 등록일
    @Column(name = "restaurant_enter")
    private LocalDateTime restaurantEnter;

    // 가게 마지막 수정일
    @Column(name = "restaurant_update")
    private LocalDateTime restaurantUpdate;

    // 가게 전화번호
    @Column(name = "restaurant_tel")
    private String restaurantTel;

    // 가게 이미지
    @Column(name = "restaurant_img")
    private String restaurantImg;

    // 가게 주소1
    @Column(name = "restaurant_addr1")
    private String restaurantAddr1;

    // 가게 주소2
    @Column(name = "restaurant_addr2")
    private String restaurantAddr2;


    public static Restaurant toEntity(RestaurantDto dto) {
        return Restaurant.builder()
                .restaurantName(dto.getRestaurantName())
                .restaurantTel(dto.getRestaurantTel())
                .restaurantImg(dto.getRestaurantImg())
                .restaurantAddr1(dto.getRestaurantAddr1())
                .restaurantAddr2(dto.getRestaurantAddr2())
                .build();
    }
}
