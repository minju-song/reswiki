package com.teddybear.reswiki.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RestaurantDto {

    // 가게 아이디
    private int restaurantId;

    // 가게 이름
    private String restaurantName;

    // 가게 주소
    private String restaurantAddress;

    // 가게 등록일
    private Date restaurantEnter;

    // 가게 마지막 수정일
    private Date restaurantUpdate;

    // 가게 전화번호
    private String restaurantTel;

    // 가게 이미지
    public String restaurantImg;

    // 가게 주소1
    public String restaurantAddr1;

    // 가게 주소2
    public String restaurantAddr2;

    // 메인홈 가게 생성자
    public RestaurantDto(int id, String name, String addr1, Date update, String img) {
        this.restaurantId = id;
        this.restaurantName = name;
        this.restaurantAddr1 = addr1;
        this.restaurantUpdate = update;
        this.restaurantImg = img;
    }
}
