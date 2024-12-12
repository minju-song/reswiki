package com.teddybear.reswiki.star.service;

import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.repository.MemberRepository;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.restaurant.repository.RestaurantRepository;
import com.teddybear.reswiki.star.dto.StarRequest;
import com.teddybear.reswiki.star.entity.Star;
import com.teddybear.reswiki.star.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StarServiceImpl implements StarService{

    private final StarRepository starRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, MemberRepository memberRepository, RestaurantRepository restaurantRepository) {
        this.starRepository = starRepository;
        this.memberRepository = memberRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public double AddStar(StarRequest.AddStarDto starDto) {

        Member member = memberRepository.findByMemberId(starDto.memberId())
                .orElseThrow(() -> new NoSuchElementException("해당하는 회원이 없습니다."));

        Restaurant restaurant = restaurantRepository.findByRestaurantId(starDto.restaurantId())
                .orElseThrow(() -> new NoSuchElementException("해당하는 가게가 없습니다."));

        Optional<Star> existingStarOpt = starRepository.findByMemberAndRestaurant(member, restaurant);

        if (existingStarOpt.isPresent()) {
            // 별점이 존재하면 업데이트

            Star existingStar = existingStarOpt.get();
            existingStar.setStarRating(starDto.StarRating());
            starRepository.save(existingStar);
        } else {
            // 별점이 존재하지 않으면 새로 저장
            Star star = Star.builder()
                    .member(member)
                    .restaurant(restaurant)
                    .starRating(starDto.StarRating())
                    .build();
            starRepository.save(star);
        }

        // 식당의 모든 별점 평균 계산
        double averageRating = starRepository.calculateAverageRating(restaurant.getRestaurantId());

        // 소수점 한 자리로 제한
        DecimalFormat df = new DecimalFormat("#.#");
        averageRating = Double.parseDouble(df.format(averageRating));

        // 식당의 평점 업데이트
        restaurant.setRestaurantStar(averageRating);
        restaurantRepository.save(restaurant);

        return averageRating;
    }
}
