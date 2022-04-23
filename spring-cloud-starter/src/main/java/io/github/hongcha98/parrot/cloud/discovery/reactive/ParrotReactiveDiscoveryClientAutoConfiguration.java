package io.github.hongcha98.parrot.cloud.discovery.reactive;

import io.github.hongcha98.parrot.cloud.ConditionalOnEnableParrotDiscovery;
import io.github.hongcha98.parrot.cloud.discovery.ParrotDiscoveryClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.ConditionalOnReactiveDiscoveryEnabled;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnReactiveDiscoveryEnabled
@ConditionalOnEnableParrotDiscovery
public class ParrotReactiveDiscoveryClientAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ReactiveDiscoveryClient reactiveDiscoveryClient(ParrotDiscoveryClient parrotDiscoveryClient) {
        return new ParrotReactiveDiscoveryClient(parrotDiscoveryClient);
    }
}
