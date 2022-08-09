package com.sparta.finalprojectback.member.repository;

import com.sparta.finalprojectback.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByEmail(String email);
}