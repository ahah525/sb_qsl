package com.ll.exam.app3.user.repository;

import com.ll.exam.app3.user.domain.SiteUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        // {noop}: 암호화되지 않은 평문 비밀번호
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

        userRepository.saveAll(Arrays.asList(u1, u2));
    }

    @Test
    @DisplayName("1번 회원을 Qsl로 가져오기")
    void t2() {
        SiteUser u1 = userRepository.getQslUser(1L);

        assertThat(u1.getId()).isEqualTo(1L);
        assertThat(u1.getUsername()).isEqualTo("user1");
        assertThat(u1.getEmail()).isEqualTo("user1@test.com");
        assertThat(u1.getPassword()).isEqualTo("{noop}1234");
    }

    @Test
    @DisplayName("2번 회원을 Qsl로 가져오기")
    void t3() {
        SiteUser u2 = userRepository.getQslUser(2L);

        assertThat(u2.getId()).isEqualTo(2L);
        assertThat(u2.getUsername()).isEqualTo("user2");
        assertThat(u2.getEmail()).isEqualTo("user2@test.com");
        assertThat(u2.getPassword()).isEqualTo("{noop}1234");
    }
}