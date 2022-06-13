package com.gifted.codes.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {

    private final AppConfiguration appConfiguration;
    private final BeanFactory beanFactory;

    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        threadPoolTaskExecutor.setCorePoolSize(appConfiguration.asyncMinPoolSize());
        threadPoolTaskExecutor.setMaxPoolSize(appConfiguration.asyncMaxPoolSize());
        threadPoolTaskExecutor.initialize();


        //https://github.com/spring-cloud/spring-cloud-sleuth/issues/2100
        final var delegatingSecurityContextAsyncTaskExecutor = new DelegatingSecurityContextAsyncTaskExecutor(threadPoolTaskExecutor);
        return new LazyTraceExecutor(beanFactory, delegatingSecurityContextAsyncTaskExecutor);
    }
}

