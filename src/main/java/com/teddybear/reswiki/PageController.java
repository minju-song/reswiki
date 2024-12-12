package com.teddybear.reswiki;

import com.teddybear.reswiki.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final RestaurantService restaurantService;

    @Autowired
    public PageController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // 메인 홈
    @GetMapping("/")
    public String home() {return "home/index.html";}

    // 로그인
    @GetMapping("/loginForm")
    public String login() {return "login/login.html";}

    // 회원가입
    @GetMapping("/signupForm")
    public String signup() {return "login/signup.html";}

    // 가게 상세 페이지
//    @GetMapping("/restaurantPage")
//    public String restaurantPage(@RequestParam("id") String id, Model model) {
//        RestaurantDto r = restaurantService.findByRestaurantId(id);
//
//        model.addAttribute("restaurant", r);
//
//        System.out.println(r.getRestaurantName());
//
//        return "restaurant/restaurantPage.html";
//    }

    // 가게 추가
    @GetMapping("/addRestaurantPage")
    public String addRestaurantPage() {
        return "restaurant/addRestaurantPage.html";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String mypage() {
        return "menu/mypage.html";
    }

    // 검색 페이지
    @GetMapping("/searchPage")
    public String searchPage() {
        return "menu/search.html";
    }
}
