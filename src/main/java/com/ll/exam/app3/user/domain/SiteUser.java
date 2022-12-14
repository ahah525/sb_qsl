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
    // orphanRemoval : interestKeywords(부모)에서 객체를 삭제하면 interestKeyword(자식)에서 자동 삭제 (OneToMany에서만 씀)
    @Builder.Default    // Builder 객체 생성될 때, 값이 보존됨(값을 넣지 않아도 null 안들어감)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<InterestKeyword> interestKeywords = new HashSet<>();    // 등록 키워드들

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<SiteUser> followers = new HashSet<>();  // 팔로워

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<SiteUser> followings = new HashSet<>(); // 팔로잉

    public void addInterestKeywordContent(String keywordContent) {
        interestKeywords.add(new InterestKeyword(this, keywordContent));
    }

    public void removeInterestKeywordContent(String keywordContent) {
        // orphanRemoval = true 로 설정되었을 때, 해당 메서드 실행시 자동으로 interestRepository.delete();
        interestKeywords.remove(new InterestKeyword(this, keywordContent));
    }

    public void follow(SiteUser following) {
        if(following == this) return;
        if(following == null) return;
        if(this.getId() == following.getId()) return;

        following.getFollowers().add(this);
        getFollowings().add(following);
    }

    public Set<SiteUser> getFollowers() {
        return followers;
    }

    public Set<SiteUser> getFollowings() {
        return followings;
    }
}
