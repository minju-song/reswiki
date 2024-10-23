package com.teddybear.reswiki.restaurant.dto;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.review.dto.ReviewResponse;
import com.teddybear.reswiki.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantResponse {

    // 메인홈 새로운 리뷰 등록 리스트
    public record GetNewList(
            List<SearchRestaurantDto> restaurants
    ) {
        public static GetNewList of(List<Restaurant> list) {
            return new GetNewList(list.stream()
                    .map(SearchRestaurantDto::new)
                    .toList());
        }
    }

    // 검색리스트
    public record Search(
            List<SearchRestaurantDto> restaurants, // 식당 목록
            long totalElements,                // 전체 요소 수
            int totalPages,                    // 전체 페이지 수
            int number,                        // 현재 페이지 번호
            int size
    ) {
        public static Search of(Page<Restaurant> restaurantPage) {
            return new Search(
                    restaurantPage.getContent().stream()
                            .map(SearchRestaurantDto::new) // Restaurant를 searchRestaurantDto으로 변환
                            .toList(),
                    restaurantPage.getTotalElements(), // 전체 요소 수
                    restaurantPage.getTotalPages(),     // 전체 페이지 수
                    restaurantPage.getNumber(),         // 현재 페이지 번호
                    restaurantPage.getSize()            // 페이지 크기
            );
        }
    }

    // 검색
    public record SearchRestaurantDto(
            String restaurantId,
            String restaurantName,
            String  restaurantImg,
            String restaurantAddr1
    ) {
        public SearchRestaurantDto(Restaurant r) {
            this(
                    r.getRestaurantId(),
                    r.getRestaurantName(),
                    r.getRestaurantImg(),
                    r.getRestaurantAddr1()
            );
        }
    }

    // 식당 상세페이지
    public record GetRestaurant(
            String restaurantId,
            String restaurantName,
            LocalDateTime restaurantUpdate,
            String restaurantTel,
            String restaurantImg,
            String restaurantAddr1,
            String restaurantAddr2,
            List<ReviewResponse.GetReview> reviews
    ) {
        public GetRestaurant(Restaurant r, List<Review> reviews) {
            this(
                    r.getRestaurantId(),
                    r.getRestaurantName(),
                    r.getRestaurantUpdate(),
                    r.getRestaurantTel(),
                    r.getRestaurantImg(),
                    r.getRestaurantAddr1(),
                    r.getRestaurantAddr2(),
                    reviews.stream()
                            .map(ReviewResponse.GetReview::new)
                            .toList()
            );
        }
    }
}
