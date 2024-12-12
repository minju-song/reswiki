package com.teddybear.reswiki.restaurant.dto;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public class RestaurantResponse {

    // Restaurant 엔티티를 SimpleRestaurantDto리스트로 변환
    public record Home(
            List<ListRestaurantDto> restaurants,
            long totalElements,                // 전체 요소 수
            int totalPages,                    // 전체 페이지 수
            int number,                        // 현재 페이지 번호
            int size
    ) {
        public static Home of(Page<Restaurant> page) {
            return new Home(page.getContent().stream()
                    .map(ListRestaurantDto::from) // DTO로 변환
                    .toList(),
                    page.getTotalElements(), // 전체 요소 수
                    page.getTotalPages(),     // 전체 페이지 수
                    page.getNumber(),         // 현재 페이지 번호
                    page.getSize()
            );
        }
    }

    // 검색리스트
    public record Search(
            List<ListRestaurantDto> restaurants, // 식당 목록
            long totalElements,                // 전체 요소 수
            int totalPages,                    // 전체 페이지 수
            int number,                        // 현재 페이지 번호
            int size
    ) {
        public static Search from(Page<Restaurant> restaurantPage) {
            return new Search(
                    restaurantPage.getContent().stream()
                            .map(ListRestaurantDto::from) // Restaurant를 searchRestaurantDto으로 변환
                            .toList(),
                    restaurantPage.getTotalElements(), // 전체 요소 수
                    restaurantPage.getTotalPages(),     // 전체 페이지 수
                    restaurantPage.getNumber(),         // 현재 페이지 번호
                    restaurantPage.getSize()            // 페이지 크기
            );
        }
    }

    // 간단한 식당 정보
    public record ListRestaurantDto(
            String restaurantId,
            String restaurantName,
            String  restaurantImg,
            String restaurantAddr1,
            double restaurantStar
    ) {
        public static ListRestaurantDto from(Restaurant r) {
            return new ListRestaurantDto(
                    r.getRestaurantId(),
                    r.getRestaurantName(),
                    r.getRestaurantImg(),
                    r.getRestaurantAddr1(),
                    r.getRestaurantStar()
            );
        }
    }

    // 식당 상세페이지
    public record RestaurantDto(
            String restaurantId,
            String restaurantName,
            String restaurantTel,
            String restaurantImg,
            String restaurantAddr1,
            String restaurantAddr2,
            double restaurantStar
    ) {
        public static RestaurantDto from(Restaurant r) {
            return new RestaurantDto(
                    r.getRestaurantId(),
                    r.getRestaurantName(),
                    r.getRestaurantTel(),
                    r.getRestaurantImg(),
                    r.getRestaurantAddr1(),
                    r.getRestaurantAddr2(),
                    r.getRestaurantStar()
            );
        }
    }
}
