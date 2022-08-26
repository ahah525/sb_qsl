package com.ll.exam.app3.user.repository;

import com.ll.exam.app3.user.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long>, UserRepositoryCustom {
}
