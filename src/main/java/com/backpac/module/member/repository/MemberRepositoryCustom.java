package com.backpac.module.member.repository;

import com.backpac.module.member.entity.Member;
import com.backpac.module.member.dto.condition.MemberCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MemberRepositoryCustom {
    Page<Member> searchPage(MemberCond cond, Pageable pageable);
}
