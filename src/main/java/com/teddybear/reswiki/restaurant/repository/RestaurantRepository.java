package com.teddybear.reswiki.restaurant.repository;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    List<Restaurant> findTop5ByOrderByRestaurantUpdateDesc();

    Optional<Restaurant> findByRestaurantName(String restaurantName);

    Optional<Restaurant> findByRestaurantId(String id);
}
