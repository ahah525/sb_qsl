package com.ll.exam.app3.user.repository;

import com.ll.exam.app3.user.domain.QSiteUser;
import com.ll.exam.app3.user.domain.SiteUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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
//        return jpaQueryFactory
//                .select(QSiteUser.siteUser)
//                .from(QSiteUser.siteUser)
//                .where(QSiteUser.siteUser.id.eq(1L))
//                .fetch();
        return null;

    }
}
