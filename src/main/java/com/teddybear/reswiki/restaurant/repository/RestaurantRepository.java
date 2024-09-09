package com.teddybear.reswiki.restaurant.repository;

import com.teddybear.reswiki.restaurant.entity.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    List<Restaurant> findTop5ByOrderByRestaurantUpdateDesc();


}
