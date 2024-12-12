package com.teddybear.reswiki.star.dto;

import jakarta.validation.constraints.NotEmpty;

public class StarRequest {

    public record AddStarDto(
            @NotEmpty
            String memberId,
            @NotEmpty
            String restaurantId,
            @NotEmpty
            double StarRating
    ) {

    }
}
