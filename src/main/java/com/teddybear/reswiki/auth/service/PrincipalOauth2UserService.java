package com.teddybear.reswiki.auth.service;

import com.teddybear.reswiki.auth.entity.GoogleUserInfo;
import com.teddybear.reswiki.auth.entity.KakaoUserInfo;
import com.teddybear.reswiki.auth.entity.OAuth2UserInfo;
import com.teddybear.reswiki.auth.entity.PrincipalDetails;
import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.entity.Role;
import com.teddybear.reswiki.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberRepository memberRepository;

    @Autowired
    public PrincipalOauth2UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 구글로부터 받은 userRequest 데이터에 대한 후처리 함수
    // 함수 종료 시 @AuthenticationPrincipal 어노테이션이 만들어짐
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 구글 로그인 버튼 클릭 -> 구글 로그인창에서 로그인 완료 -> code 리턴 -> access token 요청
        // 위까지가 userRequest 정보 -> 토대로 loadUser함수 호출 -> 구글로부터 회원프로필 받음

        // 우리 서버 기본 정보
//        System.out.println("getClientRegistration : "+userRequest.getClientRegistration());
//        System.out.println("getAccessToken : "+userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("getAttributes : "+ oAuth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            System.out.println("카카오 로그인");
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else {
            oAuth2UserInfo = null;
        }

        // 회원가입 강제 진행
        String provider = oAuth2UserInfo.getProvider(); //google
        String providerId = oAuth2UserInfo.getProvideId();
        String memberId = providerId;
        String password = bCryptPasswordEncoder.encode("비밀번호");
        String memberNickname = oAuth2UserInfo.getNickname();

        System.out.println(provider+" // "+memberId+" // "+password+" // "+memberNickname);

        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        Member member;
        if (!optionalMember.isPresent()) {
            member = Member.builder()
                    .memberId(memberId)
                    .memberPassword(password)
                    .memberNickname(memberNickname)
                    .provider(provider)
                    .providerId(providerId)
                    .memberRole(Role.ROLE_USER)
                    .build();

            memberRepository.save(member);
        }
        else {
            member = optionalMember.get();

            System.out.println("이미 소셜로 로그인 했었음");
        }

        if(provider.equals("kakao")) {
            return new PrincipalDetails(member, (Map) oAuth2User.getAttributes().get("kakao_account"));
        }
        else return new PrincipalDetails(member, oAuth2User.getAttributes());
    }
}
