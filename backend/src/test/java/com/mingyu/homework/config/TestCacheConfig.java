package com.mingyu.homework.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Test configuration that disables Redis caching for tests.
 * This configuration uses a NoOpCacheManager that doesn't actually cache anything.
 */
@TestConfiguration
public class TestCacheConfig {

    @Bean
    @Primary
    public CacheManager cacheManager() {
        return new NoOpCacheManager();
    }
}