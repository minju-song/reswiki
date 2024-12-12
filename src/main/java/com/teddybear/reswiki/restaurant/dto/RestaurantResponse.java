package com.teddybear.reswiki.restaurant.dto;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.review.dto.ReviewResponse;
import com.teddybear.reswiki.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantResponse {

    // Restaurant 엔티티를 SimpleRestaurantDto리스트로 변환
    public record Home(
            List<SimpleRestaurantDto> restaurants
    ) {
        public static Home of(List<Restaurant> list) {
            return new Home(list.stream()
                    .map(SimpleRestaurantDto::from)
                    .toList());
        }
    }

    // 검색리스트
    public record Search(
            List<SimpleRestaurantDto> restaurants, // 식당 목록
            long totalElements,                // 전체 요소 수
            int totalPages,                    // 전체 페이지 수
            int number,                        // 현재 페이지 번호
            int size
    ) {
        public static Search from(Page<Restaurant> restaurantPage) {
            return new Search(
                    restaurantPage.getContent().stream()
                            .map(SimpleRestaurantDto::from) // Restaurant를 searchRestaurantDto으로 변환
                            .toList(),
                    restaurantPage.getTotalElements(), // 전체 요소 수
                    restaurantPage.getTotalPages(),     // 전체 페이지 수
                    restaurantPage.getNumber(),         // 현재 페이지 번호
                    restaurantPage.getSize()            // 페이지 크기
            );
        }
    }

    // 간단한 식당 정보
    public record SimpleRestaurantDto(
            String restaurantId,
            String restaurantName,
            String  restaurantImg,
            String restaurantAddr1
    ) {
        public static SimpleRestaurantDto from(Restaurant r) {
            return new SimpleRestaurantDto(
                    r.getRestaurantId(),
                    r.getRestaurantName(),
                    r.getRestaurantImg(),
                    r.getRestaurantAddr1()
            );
        }
    }

    // 식당 상세페이지
    public record RestaurantDto(
            String restaurantId,
            String restaurantName,
            LocalDateTime restaurantUpdate,
            String restaurantTel,
            String restaurantImg,
            String restaurantAddr1,
            String restaurantAddr2,
            List<ReviewResponse.ReviewDto> reviews
    ) {
        public static RestaurantDto from(Restaurant r, List<Review> reviews) {
            return new RestaurantDto(
                    r.getRestaurantId(),
                    r.getRestaurantName(),
                    r.getRestaurantUpdate(),
                    r.getRestaurantTel(),
                    r.getRestaurantImg(),
                    r.getRestaurantAddr1(),
                    r.getRestaurantAddr2(),
                    reviews.stream()
                            .map(ReviewResponse.ReviewDto::from)
                            .toList()
                    );
        }
    }
}
