package com.teddybear.reswiki.restaurant.web;

import com.teddybear.reswiki.core.api.ApiUtils;
import com.teddybear.reswiki.restaurant.dto.RestaurantResponse;
import com.teddybear.reswiki.restaurant.service.RestaurantService;
import com.teddybear.reswiki.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, ReviewService reviewService) {
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
    }

    // 최신 업데이트 식당 리스트
    @GetMapping("/getNewList")
    public ResponseEntity<?> getNewList() {
        RestaurantResponse.Home getNewList = restaurantService.getNewList();
        ApiUtils.Response<?> result = ApiUtils.success(getNewList);

        return ResponseEntity.ok().body(result);
    }

    // 식당 검색
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("keyword") String keyword,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("size") int size) {
        RestaurantResponse.Search search = restaurantService.search(keyword, page, size);
        ApiUtils.Response<?> result = ApiUtils.success(search);
        return ResponseEntity.ok().body(result);
    }

    // 식당 상세페이지
    @GetMapping("/getRestaurant")
    public ResponseEntity<?> getRestaurant(@RequestParam("id") String id) {

        RestaurantResponse.RestaurantDto getRestaurant = restaurantService.getRestaurant(id);
        ApiUtils.Response<?> result = ApiUtils.success(getRestaurant);
        return ResponseEntity.ok().body(result);
    }

//    @GetMapping("/testAdd")
//    public void testAdd() {
//        RestaurantDto dto = RestaurantDto.builder()
//                .restaurantName("테스트식당2")
//                .restaurantTel("051-111-1111")
//                .restaurantImg("맛깔정.jpeg")
//                .restaurantAddr1("부산광역시 해운대구")
//                .build();
//
//        restaurantService.addRestaurant(dto);
//    }
}
