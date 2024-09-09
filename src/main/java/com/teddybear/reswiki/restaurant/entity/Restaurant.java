package com.teddybear.reswiki.restaurant.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
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
    private Date restaurantEnter;

    // 가게 마지막 수정일
    @Column(name = "restaurant_update")
    private Date restaurantUpdate;

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

}
