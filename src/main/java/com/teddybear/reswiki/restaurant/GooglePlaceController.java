package com.teddybear.reswiki.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/googlePlace")
public class GooglePlaceController {
    private final String GOOGLE_PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json";
    private final String GOOGLE_PLACES_PHOTO = "https://maps.googleapis.com/maps/api/place/photo";
    private final String API_KEY = "AIzaSyBvQW-CP5ovvNRqOlCP6YWMc85tEQS839o";

    @PostMapping("/search")
    public ResponseEntity<?> searchPlaces(@RequestBody Map<String, String> request) {
        String keyword = request.get("keyword");
        String url = String.format("%s?input=%s&inputtype=textquery&fields=business_status,icon,type,opening_hours,formatted_address,place_id,photos,name,rating&key=%s",
                GOOGLE_PLACES_API_URL, keyword, API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(response.getBody());
            } else {
                // 상태 코드에 따라 오류 처리
                return ResponseEntity.status(response.getStatusCode()).body("Error fetching photo from Google Places API");
            }
        } catch (Exception e) {
            // 오류 로그 추가
            System.err.println("Error during Google Places API call: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while fetching from Google Places API");
        }
    }
}