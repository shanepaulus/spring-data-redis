package com.shanepaulus.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.stereotype.Component;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 24-May-2023.
 */

@Component
@Slf4j
public class RedisErrorConfiguration implements CachingConfigurer {
    @Override
    public CacheErrorHandler errorHandler() {
        return new CustomCacheErrorHandler();
    }

    static class CustomCacheErrorHandler implements CacheErrorHandler {
        @Override
        public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
            log.error("Exception occurred while trying to fetch the data from the cache for key={}, cache={}. Error >> {}", key, cache, exception.getMessage());
        }

        @Override
        public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
            log.error("Exception occurred while trying to update the cache for key {}. Cache={}, Value={}. Error={}", key, cache, value, exception.getMessage());
        }

        @Override
        public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
            log.error("Exception occurred while trying to evict cache for key {} {}. Error >> {}", key, cache, exception.getMessage());
        }

        @Override
        public void handleCacheClearError(RuntimeException exception, Cache cache) {
            log.error("Exception occurred while trying to clear cache {}. Error >> {}", cache, exception.getMessage());
        }
    }
}

