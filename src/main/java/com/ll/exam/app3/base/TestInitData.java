package com.ll.exam.app3.base;

import com.ll.exam.app3.user.domain.SiteUser;
import com.ll.exam.app3.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")    // 해당 클래스의 빈들은 테스트 모드에서만 활성화
public class TestInitData {
    // CommandLineRunner: 주로 앱 실행 직후 초기데이터 세팅, 초기화에 사용
    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            SiteUser u1 = SiteUser.builder()
                    .username("user1")
                    .password("{noop}1234")
                    .email("user1@test.com")
                    .build();

            SiteUser u2 = SiteUser.builder()
                    .username("user2")
                    .password("{noop}1234")
                    .email("user2@test.com")
                    .build();

            u1.addInterestKeywordContent("축구");
            u1.addInterestKeywordContent("농구");

//            u2.addInterestKeywordContent("농구");
            u2.addInterestKeywordContent("클라이밍");
            u2.addInterestKeywordContent("마라톤");

            userRepository.saveAll(Arrays.asList(u1, u2));
        };
    }
}
