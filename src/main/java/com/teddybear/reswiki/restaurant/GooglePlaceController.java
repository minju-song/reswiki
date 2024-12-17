package com.teddybear.reswiki.restaurant;

import com.teddybear.reswiki.core.errors.exception.Exception400;
import com.teddybear.reswiki.core.errors.exception.Exception404;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/googlePlaces")
public class GooglePlaceController {
    private final String GOOGLE_PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json";
    private final String GOOGLE_PLACES_PHOTO = "https://maps.googleapis.com/maps/api/place/photo";
    private final String GOOGLE_PLACES_DETAIL = "https://maps.googleapis.com/maps/api/place/details/json";
    private final String API_KEY = "AIzaSyBvQW-CP5ovvNRqOlCP6YWMc85tEQS839o";



    @GetMapping("/search")
    public Object searchPlaces(@RequestParam("keyword") String keyword) {

        Map<String, Object> result = new HashMap<>();
        String url = String.format("%s?input=%s&inputtype=textquery&fields=type,opening_hours,formatted_address,place_id,photos,name&key=%s",
                GOOGLE_PLACES_API_URL, keyword, API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        try {
            // 1
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                //2번
                Map<String, Object> responseBody = response.getBody();
                if (responseBody != null && responseBody.containsKey("candidates")) {
                    // 3번
                    List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");

                    if (!candidates.isEmpty()) {
                        result.put("formatted_address", candidates.get(0).get("formatted_address"));
                        result.put("type", candidates.get(0).get("type"));
                        result.put("opening_hours", candidates.get(0).get("opening_hours"));
                        result.put("photos",candidates.get(0).get("photos"));
                        result.put("name", candidates.get(0).get("name"));
                        // 첫 번째 candidate의 place_id 추출
                        String placeId = (String) candidates.get(0).get("place_id");

                        result.put("place_id", placeId);

                        String detailUrl = String.format("%s?place_id=%s&fields=formatted_phone_number,opening_hours,formatted_address&key=%s",
                                GOOGLE_PLACES_DETAIL, placeId, API_KEY);

                        //1번
                        ResponseEntity<Map> responseDetail = restTemplate.getForEntity(detailUrl, Map.class);
                        //2번
                        Map<String, Object> responseBodyDetail = responseDetail.getBody();
                        System.out.println(responseBodyDetail);
                        // 3단계: "result" Map 가져오기
                        if (responseBodyDetail != null && responseBodyDetail.containsKey("result")) {
                            Map<String, Object> resultDetail = (Map<String, Object>) responseBodyDetail.get("result");

                            // 4단계: "formatted_phone_number" 추출
                            String formattedPhoneNumber = (String) resultDetail.get("formatted_phone_number");


                            result.put("formatted_phone_number", formattedPhoneNumber);
                        }

                        return result;
                    } else {
                        return Map.of("message", "No candidates found.");
                    }
                } else {
                    throw new Exception404("검색 값이 없습니다.");
                }
            } else {
                // 상태 코드에 따라 오류 처리
                throw new Exception400("구글 API 오류");
            }
        } catch (Exception e) {
            //에러
            System.out.println(e);
            return e;
        }
    }

}