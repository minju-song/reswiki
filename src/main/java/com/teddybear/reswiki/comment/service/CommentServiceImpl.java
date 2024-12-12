package com.teddybear.reswiki.comment.service;

import com.teddybear.reswiki.comment.dto.CommentRequest;
import com.teddybear.reswiki.comment.dto.CommentResponse;
import com.teddybear.reswiki.comment.entity.Comment;
import com.teddybear.reswiki.comment.repository.CommentRepository;
import com.teddybear.reswiki.core.errors.exception.Exception404;
import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.repository.MemberRepository;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, RestaurantRepository restaurantRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.restaurantRepository= restaurantRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public CommentResponse.Comments getComments(int size, int page, String restaurantId) {
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new Exception404("가게가 존재하지 않습니다."));

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Comment> comments = commentRepository.findByRestaurantOrderByCommentDateDesc(restaurant, pageable);

        return CommentResponse.Comments.of(comments);

    }

    @Override
    public CommentResponse.CommentIdDto createComment(CommentRequest.JoinCommentDto joinCommentDto) {

        Restaurant restaurant = restaurantRepository.findByRestaurantId(joinCommentDto.restaurantId())
                .orElseThrow(() -> new NoSuchElementException("가게가 존재하지 않습니다."));

        Member member = memberRepository.findByMemberId(joinCommentDto.memberId())
                .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .restaurant(restaurant)
                .member(member)
                .commentCategory(joinCommentDto.commentCategory())
                .commentContents(joinCommentDto.commentContents())
                .commentDate(new Date())
                .build();

        return CommentResponse.CommentIdDto.from(commentRepository.save(comment));
    }

    @Override
    public boolean deleteComment(int commentId) {
        // 댓글이 존재하는지 확인
        if (!commentRepository.existsById(commentId)) {
            throw new NoSuchElementException("댓글이 존재하지 않습니다.");
        }

        commentRepository.deleteById(commentId);
        return true;
    }

}
