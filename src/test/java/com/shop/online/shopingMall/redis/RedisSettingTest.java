package com.shop.online.shopingMall.redis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisSettingTest {

    @Autowired
    RedisTemplate redisTemplate;

    @DisplayName("Redis 세팅 테스트")
    @Test
    public void 레디스_키벨류_테스트() {
        String key = "hello";
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, "saouk");

        String result = valueOperations.get(key);

        Assertions.assertThat("saouk").isEqualTo(result);

    }
}
