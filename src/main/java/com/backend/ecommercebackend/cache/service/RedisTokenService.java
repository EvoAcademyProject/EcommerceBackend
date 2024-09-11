package com.backend.ecommercebackend.cache.service;

import com.backend.ecommercebackend.authentication.service.JwtService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisTokenService {

  private static final String REFRESH_TOKEN_KEY_PREFIX = "refreshToken: ";

  private final RedisTemplate<String, String> redisTemplate;
  private final JwtService jwtService;

  public void storeRefreshToken(String email, String refreshToken, long expirationTime){
    String redisKey = REFRESH_TOKEN_KEY_PREFIX + email;
    try{
      redisTemplate.opsForValue().set(redisKey, refreshToken, expirationTime, TimeUnit.MILLISECONDS);
    } catch (Exception e) {
      log.error("Failed to store refresh token in Redis", e);
    }
  }

  public String getRefreshToken(String email) {
    //the key where refresh token is stored
    String redisKey= REFRESH_TOKEN_KEY_PREFIX + email;
    // the value
    var refreshToken=redisTemplate.opsForValue().get(redisKey);
    try {
      if (refreshToken == null) {
        //the new value
        var newRefreshToken=jwtService.generateRefreshToken(email);
        //setting the key and the new value, if cache is null
        redisTemplate.opsForValue().set(redisKey,newRefreshToken);
      }
    }
    catch (Exception e) {
      log.error("Failed to retrieve refresh token in Redis", e);
    }
    return refreshToken;
  }

  public boolean validateRefreshToken(String email, String providedToken) {
    String storedToken = getRefreshToken(email);
    return storedToken != null && storedToken.equals(providedToken);
  }

  public void deleteRefreshToken(String email) {
    String redisKey = REFRESH_TOKEN_KEY_PREFIX + email;
    redisTemplate.delete(redisKey);
  }
}


