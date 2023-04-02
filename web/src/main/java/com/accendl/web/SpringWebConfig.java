package com.accendl.web;

import io.lettuce.core.api.StatefulConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClusterConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionProvider;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.util.concurrent.CompletionStage;

@Configuration(proxyBeanMethods = false)
public class SpringWebConfig  {

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter("yyyy-MM-dd HH:mm:ss");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }

        };
    }

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
//        redisStandaloneConfiguration.setPassword("1234");
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(new RedisClusterConfiguration(clusterProperties.getNodes()));
    }

    /**
     * Type safe representation of application.properties
     */
    @Autowired
    private RedisClusterConfigurationProperties clusterProperties;

//    public @Bean RedisConnectionFactory connectionFactory() {
//
//        return new JedisConnectionFactory(
//                new RedisClusterConfiguration(clusterProperties.getNodes()));
//    }

}
