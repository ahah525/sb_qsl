package com.ll.exam.app3.user.repository;

import com.ll.exam.app3.user.domain.SiteUser;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.ll.exam.app3.user.domain.QSiteUser.siteUser;

// 이름은 무조건 ~Impl 이어야함
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public SiteUser getQslUser(Long id) {
        /**
         * SELECT *
         * FROM site_user
         * WHERE id = 1
         */
        return jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .where(siteUser.id.eq(id))
                .fetchOne();    // 단건 조회(없으면 null, 둘 이상이면 exception 반환)
    }

    @Override
    public long getQslCount() {
        /**
         * SELECT *
         * FROM site_user
         */
        return jpaQueryFactory
                .select(siteUser.count())
                .from(siteUser)
                .fetchOne();
    }

    @Override
    public SiteUser getQslUserOrderByIdAscOne() {
        return jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .orderBy(siteUser.id.asc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public List<SiteUser> getQslUsersOrderByIdAsc() {
        return jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .orderBy(siteUser.id.asc())
                .fetch();   // 리스트 조회
    }

    @Override
    public List<SiteUser> searchQsl(String search) {
        return jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .where(siteUser.username.contains(search)
                        .or(siteUser.email.contains(search)))
                .orderBy(siteUser.id.desc())
                .fetch();
    }

    @Override
    public Page<SiteUser> searchQsl(String kw, Pageable pageable) {
        // TODO: 페이징은 해당 요청에 대한 엘리먼트를 리스트로 조회하는 쿼리(게시글 목록 조회 용도) & 전체 엘리먼트 수를 구하는 쿼리(페이지 번호 그리는 용도) 2개 날라감
        JPAQuery<SiteUser> usersQuery = jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .where(siteUser.username.contains(kw)
                        .or(siteUser.email.contains(kw)))
                .offset(pageable.getOffset())   // 건너뛰어야하는 아이템 개수
                .limit(pageable.getPageSize());  // 가져올 아이템 개수

        // pageable sort 방식 적용
        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(siteUser.getType(), siteUser.getMetadata());
            usersQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }

        List<SiteUser> users = usersQuery.fetch();

        JPAQuery<Long> usersCountQuery = jpaQueryFactory
                .select(siteUser.count())
                .from(siteUser)
                .where(siteUser.username.contains(kw)
                        .or(siteUser.email.contains(kw)));

//        return new PageImpl<>(users, pageable, count);

        return PageableExecutionUtils.getPage(users, pageable, usersCountQuery::fetchOne);
    }
}
