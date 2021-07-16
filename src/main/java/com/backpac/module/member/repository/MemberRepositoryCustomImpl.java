package com.backpac.module.member.repository;

import com.backpac.module.member.dto.condition.MemberCond;
import com.backpac.module.common.repository.CommonPredicates;
import com.backpac.module.member.entity.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.backpac.module.member.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Member> searchPage(MemberCond cond, Pageable pageable) {
        QueryResults<Member> result = queryFactory
                .selectFrom(member)
                .where(nameEq(cond.getName()), emailEq(cond.getEmail()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Member> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanBuilder emailEq(String emailCond) {
        return CommonPredicates.nullSafeBuilder(() -> hasText(emailCond) ? member.email.eq(emailCond) : null);
    }

    private BooleanBuilder nameEq(String nameCond) {
        return CommonPredicates.nullSafeBuilder(() -> hasText(nameCond) ? member.name.eq(nameCond) : null);
    }

}
