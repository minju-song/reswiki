package com.teddybear.reswiki.restaurant.dto;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDto {

    // 가게 아이디
    private int restaurantId;

    // 가게 이름
    private String restaurantName;

    // 가게 주소
    private String restaurantAddress;

    // 가게 등록일
    private LocalDateTime restaurantEnter;

    // 가게 마지막 수정일
    private LocalDateTime restaurantUpdate;

    // 가게 전화번호
    private String restaurantTel;

    // 가게 이미지
    public String restaurantImg;

    // 가게 주소1
    public String restaurantAddr1;

    // 가게 주소2
    public String restaurantAddr2;

    public static RestaurantDto toDto(Restaurant r) {
        return RestaurantDto.builder()
                .restaurantId(r.getRestaurantId())
                .restaurantName(r.getRestaurantName())
                .restaurantAddr1(r.getRestaurantAddr1())
                .restaurantUpdate(r.getRestaurantUpdate())
                .restaurantImg(r.getRestaurantImg())
                .build();
    }
}
