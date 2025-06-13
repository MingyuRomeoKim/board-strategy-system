package com.mingyu.homework.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        ObjectMapper defaultMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 타입 정보 포함용 Mapper
        ObjectMapper typedMapper = defaultMapper.copy();
        typedMapper.activateDefaultTyping(
                BasicPolymorphicTypeValidator.builder()
                        .allowIfSubType("com.mingyu.homework.api.v1.dto.response")
                        .build(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        GenericJackson2JsonRedisSerializer defaultSerializer = new GenericJackson2JsonRedisSerializer(defaultMapper);
        GenericJackson2JsonRedisSerializer typedSerializer = new GenericJackson2JsonRedisSerializer(typedMapper);

        // default (리스트용, 타입 정보 X)
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(defaultSerializer))
                .entryTtl(Duration.ofMinutes(3));

        // detail 캐시용 (타입 정보 O)
        RedisCacheConfiguration detailConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(typedSerializer))
                .entryTtl(Duration.ofMinutes(3));

        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("postListCache", defaultConfig);
        configMap.put("postDetailCache", detailConfig);

        return RedisCacheManager.builder(redisConnectionFactory)
                .withInitialCacheConfigurations(configMap)
                .cacheDefaults(defaultConfig)
                .build();
    }
}
