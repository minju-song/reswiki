package com.teddybear.reswiki.review.web;

import com.teddybear.reswiki.review.dto.ReviewDto;
import com.teddybear.reswiki.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("review/getReviewList")
    public List<ReviewDto> getReviewList(@RequestParam("id") String id) {

        return reviewService.getNewReviewList(id);
    }
}
