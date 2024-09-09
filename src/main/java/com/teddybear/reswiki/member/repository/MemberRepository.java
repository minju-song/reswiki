package com.teddybear.reswiki.member.repository;

import com.teddybear.reswiki.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
