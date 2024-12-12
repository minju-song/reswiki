package com.teddybear.reswiki.restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/googlePlaces")
public class GooglePlaceController {
    private final String GOOGLE_PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json";
    private final String GOOGLE_PLACES_PHOTO = "https://maps.googleapis.com/maps/api/place/photo";
    private final String API_KEY = "AIzaSyBvQW-CP5ovvNRqOlCP6YWMc85tEQS839o";

    @GetMapping("/search")
    public Object searchPlaces(@RequestParam("keyword") String keyword) {
        String url = String.format("%s?input=%s&inputtype=textquery&fields=business_status,icon,type,opening_hours,formatted_address,place_id,photos,name,rating&key=%s",
                GOOGLE_PLACES_API_URL, keyword, API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                // 상태 코드에 따라 오류 처리
                return ResponseEntity.status(response.getStatusCode()).body("Error fetching photo from Google Places API");
            }
        } catch (Exception e) {
            //에러
            System.out.println(e);
            return e;
        }
    }
}