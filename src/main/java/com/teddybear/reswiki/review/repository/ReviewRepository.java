package com.teddybear.reswiki.review.repository;

import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.review.entity.IsNewest;
import com.teddybear.reswiki.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findByRestaurantIdAndIsNewest(Restaurant restaurantId, IsNewest isNewest);
}
