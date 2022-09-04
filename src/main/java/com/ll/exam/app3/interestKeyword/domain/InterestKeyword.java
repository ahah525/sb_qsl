package com.ll.exam.app3.interestKeyword.domain;

import com.ll.exam.app3.user.domain.SiteUser;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)   // 선택한 필드만 equals, hashCode로 관리
@IdClass(InterestKeywordId.class)
public class InterestKeyword {
    // 복합키
    @Id
    @ManyToOne
    @EqualsAndHashCode.Include
    private SiteUser user;

    @Id
    @EqualsAndHashCode.Include
    private String content;
}
