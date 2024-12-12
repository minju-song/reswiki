package com.teddybear.reswiki.restaurant.repository;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    // 아이디 순으로 페이지네이션하여 모든 레스토랑 가져오기
    Page<Restaurant> findAllByOrderByRestaurantIdAsc(Pageable pageable);

    // 레스토랑 이름으로 검색
    @Query("SELECT r FROM Restaurant r WHERE r.restaurantName LIKE LOWER (CONCAT('%', :name, '%'))")
    Page<Restaurant> findByRestaurantNameContaining(@Param("name") String name, Pageable pageable);

    // 레스토랑 아이디로 검색
    Optional<Restaurant> findByRestaurantId(String id);

}
