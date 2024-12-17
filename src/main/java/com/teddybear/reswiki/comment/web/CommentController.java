package com.teddybear.reswiki.comment.web;

import com.teddybear.reswiki.comment.dto.CommentRequest;
import com.teddybear.reswiki.comment.dto.CommentResponse;
import com.teddybear.reswiki.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    // 특정 식당 커멘트 조회
    @GetMapping("/{id}")
    public CommentResponse.Comments getComments(@RequestParam("size") int size, @RequestParam("page") int page,
                                         @PathVariable("id") String restaurantId) {

        return commentService.getComments(size,page,restaurantId);
    }

    // 커멘트 등록
    @PostMapping
    public CommentResponse.CommentIdDto postComment(@RequestBody CommentRequest.JoinCommentDto commentDto) {
       return commentService.createComment(commentDto);
    }

    // 커멘트 삭제
    @DeleteMapping
    public CommentResponse.CommentIdDto deleteComment(@RequestParam("commentId") int commentId) {
        return commentService.deleteComment(commentId);
    }
}
