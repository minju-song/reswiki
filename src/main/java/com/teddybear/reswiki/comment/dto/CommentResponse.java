package com.teddybear.reswiki.comment.dto;

import com.teddybear.reswiki.comment.entity.Comment;
import com.teddybear.reswiki.comment.entity.CommentCategory;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public class CommentResponse {

    public record Comments(
            List<ListCommentDto> comments,
            long totalElements,
            int totalPages,
            int number,
            int size
    ) {
        public static Comments of(Page<Comment> page) {
            return new Comments(page.getContent().stream()
                    .map(ListCommentDto::from)
                    .toList(),
                    page.getTotalElements(),
                    page.getTotalPages(),
                    page.getNumber(),
                    page.getSize()
            );
        }
    }

    public record ListCommentDto(
            int commentId,
            String  memberId,
            String restaurantId,
            CommentCategory commentCategory,
            String commentContents,
            Date commentDate
    ) {
        public static ListCommentDto from(Comment c) {
            return new ListCommentDto(
                    c.getCommentId(),
                    c.getMember().getMemberId(),
                    c.getRestaurant().getRestaurantId(),
                    c.getCommentCategory(),
                    c.getCommentContents(),
                    c.getCommentDate()
            );
        }
    }

    public record CommentIdDto(
            int commentId
    ) {
        public static CommentIdDto from(Comment comment) {
            return new CommentIdDto(
                    comment.getCommentId()
            );
        }
    }
}
