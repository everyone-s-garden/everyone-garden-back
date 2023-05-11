package com.everyonegarden.member.repository;

import com.everyonegarden.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findBySocialId(String socialId);
}
