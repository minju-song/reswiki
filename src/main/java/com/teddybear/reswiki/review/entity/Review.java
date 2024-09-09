package com.teddybear.reswiki.review.entity;

import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Review {

    // 리뷰 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    // 작성자 아이디 - FK
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writerId;

    // 가게 아이디 - FK
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

    // 리뷰 항목
    @Enumerated(EnumType.STRING)
    @Column(name = "review_item")
    private ReviewItem reviewItem;

    // 리뷰 내용
    @Column(name = "review_contents")
    private String reviewContents;

    // 최신 리뷰인지 Y/N
    @Enumerated(EnumType.STRING)
    @Column(name = "is_newest")
    private IsNewest isNewest;

    @Column(name = "review_date")
    private Date reviewDate;
}
