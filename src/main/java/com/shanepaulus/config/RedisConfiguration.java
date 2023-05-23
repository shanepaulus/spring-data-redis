package com.shanepaulus.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

  @Value("${redis.hostname}")
  private String redisHost;
  @Value("${redis.port}")
  private int redisPort;


  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMinutes(20))
        .disableCachingNullValues()
        .serializeKeysWith(
            SerializationPair.fromSerializer(new GenericToStringSerializer<>(String.class)));
  }

//  @Bean
//  @Qualifier("redisConnectionFactory")
//  public RedisStandaloneConfiguration redisConnectionFactory() {
//    return new RedisStandaloneConfiguration();
//  }

//  @Bean
//  public RedisTemplate<?, ?> redisTemplate() {
//    RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
//    redisTemplate.setConnectionFactory(redisConnectionFactory());
//    return redisTemplate;
//  }
}
