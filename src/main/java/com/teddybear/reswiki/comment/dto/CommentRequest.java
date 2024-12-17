package com.teddybear.reswiki.comment.dto;

import com.teddybear.reswiki.comment.entity.CommentCategory;
import com.teddybear.reswiki.comment.entity.CommentEmoji;
import jakarta.validation.constraints.NotEmpty;

public class CommentRequest {

    public record JoinCommentDto(
            @NotEmpty
            String restaurantId,
            @NotEmpty
            String memberId,
            @NotEmpty
            CommentCategory commentCategory,
            @NotEmpty
            String commentContents,
            @NotEmpty
            CommentEmoji commentEmoji
    ) {}
}
