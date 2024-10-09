package com.asklepios.backend_service;
import com.asklepios.backend_service.model.generated.pojo.*;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {



    @Bean(name = "redisTemplateLov")
    public RedisTemplate<String, ApLov> redisTemplateLov(RedisConnectionFactory factory) {
        final RedisTemplate<String, ApLov> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(ApLov.class));
        return template;
    }
    @Bean(name = "redisTemplateTranslation")
    public RedisTemplate<String, ApTranslation> redisTemplateTranslation(RedisConnectionFactory factory) {
        final RedisTemplate<String, ApTranslation> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(ApTranslation.class));
        return template;
    }
    @Bean(name = "stringRedisTemplate1")
    public RedisTemplate<String, String> stringRedisTemplate1(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }

    @Bean(name = "redisTemplateGlobalSettings")
    public RedisTemplate<String, ApGlobalSettings> redisTemplateGlobalSettings(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ApGlobalSettings> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(ApGlobalSettings.class));
        return template;
    }

    @Bean(name = "redisTemplateMessages")
    public RedisTemplate<String, ApMessages> redisTemplateMessages(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ApMessages> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(ApMessages.class));
        return template;
    }
    @Bean(name = "redisTemplateLovValuesList")
    public RedisTemplate<String, ApLovValues> redisTemplateLovValuesList(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ApLovValues> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(ApLovValues.class));
        return template;
    }
    @Bean
    public Jedis jedis() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379);
        return jedisPool.getResource();
    }
}

