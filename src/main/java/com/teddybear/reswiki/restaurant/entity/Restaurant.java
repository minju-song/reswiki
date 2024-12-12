package com.teddybear.reswiki.restaurant.entity;

//import com.teddybear.reswiki.restaurant.dto.RestaurantDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    // 가게 ID
    @Id
    @Column(name = "restaurant_id", unique = true)
    private String restaurantId;

    // 가게 이름
    @Column(name = "restaurant_name")
    private String restaurantName;

    // 가게 전화번호
    @Column(name = "restaurant_tel")
    private String restaurantTel;

    // 가게 이미지
    @Column(name = "restaurant_img", columnDefinition = "TEXT")
    private String restaurantImg;

    // 가게 주소1
    @Column(name = "restaurant_addr1")
    private String restaurantAddr1;

    // 가게 주소2
    @Column(name = "restaurant_addr2")
    private String restaurantAddr2;

    // 평균 별점
    @Column(name = "restaurant_star")
    private double restaurantStar;

}
