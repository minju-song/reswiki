package com.teddybear.reswiki.comment.repository;

import com.teddybear.reswiki.comment.entity.Comment;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findByRestaurantOrderByCommentDateDesc(Restaurant restaurant, Pageable pageable);
}
