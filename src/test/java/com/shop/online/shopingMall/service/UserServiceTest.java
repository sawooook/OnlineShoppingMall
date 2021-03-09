package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Address;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.user.UserLoginResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;


    @Test
    @DisplayName("로그인 성공 테스트")
    public void 로그인_성공() {
        User testUser = User.builder().name("test").password(passwordEncoder.encode("123")).phone("010").address(new Address("seoul", "test"))
                .email("saouk@naver.com").build();

        entityManager.persist(testUser);

        UserLoginResponseDto userLoginResponseDto = userService.loginCheck(testUser.getEmail(), "123");

        assertThat(userLoginResponseDto.getName()).isEqualTo("test");

    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void 로그인_실패() {
        User testUser = User.builder().name("test").password("123").phone("010").address(new Address("seoul", "test"))
                .email("saouk@naver.com").build();

        entityManager.persist(testUser);

        try {
            userService.loginCheck(testUser.getEmail(), "123");
        } catch (Exception e) {
            Assertions.assertEquals("로그인 실패", e.getMessage());
        }
    }

    @Test
    @DisplayName("이메일 중복여부 테스트")
    public void 이메일_중복여부_테스트() {
        User testUser = User.builder().name("test").password("123").phone("010").address(new Address("seoul", "test"))
                .email("saouk@naver.com").build();
        entityManager.persist(testUser);

        boolean checkStatus = userService.checkEmail("saouk@naver.com");

        assertThat(checkStatus).isTrue();
    }
}