package com.teddybear.reswiki.restaurant;

//import com.teddybear.reswiki.restaurant.dto.RestaurantDto;

import com.teddybear.reswiki.restaurant.repository.RestaurantRepository;
import com.teddybear.reswiki.restaurant.service.RestaurantServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
@SpringBootTest
public class RestaurantServiceTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantServiceImpl restaurantService;


    @Test
    void 가게등록() {
//        RestaurantResponse.RestaurantDto dto = RestaurantDto.builder()
//                        .restaurantName("테스트식당")
//                                .restaurantTel("051-111-1111")
//                                        .restaurantImg("맛깔정.jpeg")
//                                                .restaurantAddr1("부산광역시 해운대구")
//                                                        .build();
//
//        Restaurant result = restaurantService.addRestaurant(dto);
//        Restaurant result2 = restaurantService.addRestaurant(dto);

//        assertThat(result.getRestaurantName()).isEqualTo("테스트식당");




    }
}
