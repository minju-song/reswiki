package com.teddybear.reswiki.restaurant;

import com.teddybear.reswiki.restaurant.dto.RestaurantDto;
import com.teddybear.reswiki.restaurant.entity.Restaurant;
import com.teddybear.reswiki.restaurant.repository.RestaurantRepository;
import com.teddybear.reswiki.restaurant.service.RestaurantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
@DataJpaTest
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void 가게등록() {
        RestaurantDto dto = RestaurantDto.builder()
                        .restaurantName("테스트식당")
                                .restaurantTel("051-111-1111")
                                        .restaurantImg("맛깔정.jpeg")
                                                .restaurantAddr1("부산광역시 해운대구")
                                                        .build();

        Restaurant result = restaurantService.addRestaurant(dto);

        assertThat(result.getRestaurantName()).isEqualTo("테스트식당");




    }
}
