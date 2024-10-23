package com.teddybear.reswiki.restaurant.repository;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    // 최신 업데이트 식당 5개
    List<Restaurant> findTop5ByOrderByRestaurantUpdateDesc();

    // 레스토랑 이름으로 검색
    Page<Restaurant> findByRestaurantName(String restaurantName, Pageable pageable);

    @Query("SELECT r FROM Restaurant r WHERE r.restaurantName LIKE LOWER (CONCAT('%', :name, '%'))")
    Page<Restaurant> findByRestaurantNameContaining(@Param("name") String name, Pageable pageable);

    // 레스토랑 아이디로 검색
    Optional<Restaurant> findByRestaurantId(String id);

}
