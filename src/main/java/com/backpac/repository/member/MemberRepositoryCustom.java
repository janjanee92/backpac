package com.backpac.repository.member;

import com.backpac.domain.Member;
import com.backpac.dto.condition.MemberCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MemberRepositoryCustom {
    Page<Member> searchPage(MemberCond cond, Pageable pageable);
}
