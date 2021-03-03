package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager entityManager;

    public Long save(User user) {
        entityManager.persist(user);
        return user.getId();
    }

    public User findUser(Long id) {
        return entityManager.find(User.class, id);
    }
}
