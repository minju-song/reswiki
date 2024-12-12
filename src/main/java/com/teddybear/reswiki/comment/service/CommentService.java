package com.teddybear.reswiki.comment.service;

import com.teddybear.reswiki.comment.dto.CommentRequest;
import com.teddybear.reswiki.comment.dto.CommentResponse;

public interface CommentService {

    // 커멘트 조회
    CommentResponse.Comments getComments(int size, int page, String restaurantId);

    // 커멘트 등록
    CommentResponse.CommentIdDto createComment(CommentRequest.JoinCommentDto comment);

    // 커멘트 삭제
    boolean deleteComment(int commentId);
}
