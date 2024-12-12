package com.teddybear.reswiki.member;

import com.teddybear.reswiki.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    void 회원가입() {
//        MemberDto dto = MemberDto.builder()
//                .memberId("aaaa")
//                .memberPassword("aaaa")
//                .memberNickname("aaaa")
//                .memberRole(Role.ROLE_USER)
//                .build();
//
//        Member result = memberService.joinMember(dto);

//        assertThat(result.getMemberId()).isEqualTo("aaaa");


    }
}
