package com.gift.kakao.domain.member.dao;

import com.gift.kakao.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
