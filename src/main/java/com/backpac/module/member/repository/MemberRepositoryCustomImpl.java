package com.backpac.module.member.repository;

import com.backpac.module.member.dto.condition.MemberCond;
import com.backpac.module.member.entity.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Supplier;

import static com.backpac.module.member.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Member> searchPage(MemberCond cond, Pageable pageable) {

        JPAQuery<Member> query = queryFactory
                .selectFrom(member)
                .where(nameEq(cond.getName()), emailEq(cond.getEmail()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        sort(pageable, query);

        QueryResults<Member> result = query.fetchResults();
        List<Member> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private void sort (Pageable pageable, JPAQuery<Member> query) {
        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(member.getType(),
                    member.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }
    }

    private BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }

    private BooleanBuilder emailEq(String emailCond) {
        return nullSafeBuilder(() -> hasText(emailCond) ? member.email.eq(emailCond) : null);
    }

    private BooleanBuilder nameEq(String nameCond) {
        return nullSafeBuilder(() -> hasText(nameCond) ? member.name.eq(nameCond) : null);
    }

}
