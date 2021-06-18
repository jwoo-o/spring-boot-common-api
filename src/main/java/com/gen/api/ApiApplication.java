package com.gen.api;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAspectJAutoProxy
@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
@EnableEncryptableProperties
@Slf4j
public class ApiApplication implements CommandLineRunner {


    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${file.uri.path}")
    private String resourcesUriPath;

    @Value("${server.servlet.context-path}")
    private String basicUrl;

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

    }
}
