package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;
    @Test
    public void 토큰리스트_가져오기() {
        User user1 = User.builder().name("test").pushToken("test").build();
        User user2 = User.builder().name("test").pushToken("test").build();

        entityManager.persist(user1);
        entityManager.persist(user2);

        List<String> result = userRepository.pushOnUser();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }

}