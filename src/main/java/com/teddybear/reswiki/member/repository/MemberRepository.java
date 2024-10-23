package com.teddybear.reswiki.member.repository;

import com.teddybear.reswiki.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    // 회원조회
    Optional<Member> findByMemberId(String memberId);

    // 중복체크
    boolean existsByMemberId(String memberId);

}
