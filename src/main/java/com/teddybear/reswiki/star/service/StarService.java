package com.teddybear.reswiki.star.service;

import com.teddybear.reswiki.star.dto.StarRequest;

public interface StarService {

    double AddStar(StarRequest.AddStarDto starDto);
}
