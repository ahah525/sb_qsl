package com.ll.exam.app3.user.repository;

import com.ll.exam.app3.user.domain.SiteUser;

public interface UserRepositoryCustom {
    SiteUser getQslUser(Long id);
    long getQslCount();

    SiteUser getQslUserOrderByIdAscOne();
}
