package com.shanepaulus.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 24-May-2023.
 */

@Configuration
public class RedisConfig {

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    RedisCacheWriter cacheWriter = RedisCacheWriter
        .nonLockingRedisCacheWriter(redisConnectionFactory);
    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
        .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

    Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
    cacheConfigurations.put("users", redisCacheConfiguration.entryTtl(Duration.ofSeconds(30)));

    return RedisCacheManager.builder(redisConnectionFactory)
        .withInitialCacheConfigurations(cacheConfigurations).cacheWriter(cacheWriter)
        .transactionAware().build();
  }
}
