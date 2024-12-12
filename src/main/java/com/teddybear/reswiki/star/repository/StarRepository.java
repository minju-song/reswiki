package com.teddybear.reswiki.star.repository;

import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.star.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<Star, Integer> {

    // 별점 찾기
    Optional<Star> findByMemberAndRestaurant(Member member, Restaurant restaurant);

    // 식당의 평균 별점 계산
    @Query("SELECT AVG(s.starRating) FROM Star s WHERE s.restaurant.id = :restaurantId")
    double calculateAverageRating(@Param("restaurantId") String restaurantId);
}
