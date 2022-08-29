package com.ll.exam.app3.user.repository;

import com.ll.exam.app3.user.domain.SiteUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.ll.exam.app3.user.domain.QSiteUser.*;

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
}
