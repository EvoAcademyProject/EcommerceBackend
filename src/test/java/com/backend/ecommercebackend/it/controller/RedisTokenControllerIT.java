package com.backend.ecommercebackend.it.controller;

import com.backend.ecommercebackend.cache.service.RedisTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class RedisTokenControllerIT {

  private static final String REFRESH_TOKEN = "eyJhbGciOiJIUzUxMiJ9" +
          ".eyJzdWIiOiJ5ZWdhbmFAZXhhbXBsZS5jb20iLCJpYXQiOjE3MjYxNDU3MDcsImV4cCI6MTcyODczNzcwN30.5KCM62V2Txk_o9FraqfozlcmcfEYr9QEcMKf_ZY_4_CiTRWfYD7stwy029Y6eFFhBJLtGsAjNPW_ZqG1C7gVhw";

  @Autowired
  private RedisTemplate<String, String> redisTemplate;
  @Autowired
  RedisTokenService redisTokenService;
  @Autowired
  private MockMvc mockMvc;


  @BeforeEach
  void resetRedis() {
    redisTemplate.keys("*refresh_token:yegana@example.com*").forEach(redisTemplate::delete);
  }

  @Test
  @DisplayName("Store and retrieve refresh token")
  void shouldStoreAndRetrieveRefreshToken() throws Exception {

    mockMvc.perform(post("/api/v1/auth/store")
                    .param("email","yegana@example.com")
                    .param("token",REFRESH_TOKEN)
                    .param("expirationTime", String.valueOf(2592000000L)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Token stored successfully"));

    var storedToken = redisTokenService.getRefreshToken("yegana@example.com");
    var refreshTokenCache = redisTemplate.opsForValue().get("refresh_token:yegana@example.com");

    assertThat(refreshTokenCache).isNotNull();
    assertThat(storedToken).isEqualTo(REFRESH_TOKEN);
  }
}