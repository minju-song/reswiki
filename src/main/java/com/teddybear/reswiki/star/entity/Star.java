package com.teddybear.reswiki.star.entity;

import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "stars",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"member_id", "restaurant_id"})
        }
)
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "star_id")
    private int starId;

    // 작성자 아이디 - FK
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 가게 아이디 - FK
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "star_rating", nullable = false)
    private double starRating;

}
