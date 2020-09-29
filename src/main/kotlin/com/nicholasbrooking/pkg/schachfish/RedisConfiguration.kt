package com.nicholasbrooking.pkg.schachfish

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories


@Configuration
@EnableRedisRepositories
class RedisConfiguration {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return JedisConnectionFactory()
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val template: RedisTemplate<*, *> = RedisTemplate<Any, Any>()
        template.setConnectionFactory(redisConnectionFactory())
        return template
    }
}