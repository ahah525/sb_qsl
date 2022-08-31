package com.ll.exam.app3.user.repository;

import com.ll.exam.app3.user.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<SiteUser, Long>, UserRepositoryCustom {
    List<SiteUser> findByInterestKeywords_content(String ik);
}
