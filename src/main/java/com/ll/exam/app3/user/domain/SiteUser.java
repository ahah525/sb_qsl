package com.ll.exam.app3.user.domain;

import com.ll.exam.app3.interestKeyword.domain.InterestKeyword;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder    // 빌더는 내부적으로 전체 인자 생성자를 호출(전체 인자 생성자 필요)
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    // TODO: Set(InterestKeyword)에서 hashCode, equals 오버라이드 필수
    // SiteUser : InterestKeyword = m : n
    @Builder.Default    // Builder 객체 생성될 때, 값이 보존됨(값을 넣지 않아도 null 안들어감)
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<InterestKeyword> interestKeywords = new HashSet<>();    // 등록 키워드들

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<SiteUser> followers = new HashSet<>();

    public void addInterestKeywordContent(String keywordContent) {
        interestKeywords.add(new InterestKeyword(keywordContent));
    }

    public void addFollower(SiteUser follower) {
        followers.add(follower);
    }
}
