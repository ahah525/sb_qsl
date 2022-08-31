package com.ll.exam.app3.interestKeyword.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)   // 선택한 필드만 equals, hashCode로 관리
public class InterestKeyword {
    @Id
    @EqualsAndHashCode.Include
    private String content;
}
