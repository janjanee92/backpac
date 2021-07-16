package com.backpac.module.member.repository;

import com.backpac.module.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
    Optional<Member> findByEmailOrNickname(String email, String nickname);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}
