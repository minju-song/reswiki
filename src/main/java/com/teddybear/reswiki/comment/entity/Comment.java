package com.teddybear.reswiki.comment.entity;

import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    // 리뷰 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    // 작성자 아이디 - FK
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 가게 아이디 - FK
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // 리뷰 항목
    @Enumerated(EnumType.STRING)
    @Column(name = "comment_category")
    private CommentCategory commentCategory;

    // 리뷰 내용
    @Column(name = "comment_contents")
    private String commentContents;

    @Column(name = "comment_date")
    private Date commentDate;
}
