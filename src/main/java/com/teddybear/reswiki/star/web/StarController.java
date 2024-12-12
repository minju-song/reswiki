package com.teddybear.reswiki.star.web;

import com.teddybear.reswiki.star.dto.StarRequest;
import com.teddybear.reswiki.star.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stars")
public class StarController {

    private final StarService starService;

    @Autowired
    public StarController(StarService starService) {
        this.starService = starService;
    }

    @PostMapping
    public Object postStar(@RequestBody StarRequest.AddStarDto starDto) {
        return starService.AddStar(starDto);
    }
}
