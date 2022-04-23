package io.github.hongcha98.parrot.cloud.discovery;

import io.github.hongcha98.parrot.client.naming.NamingServer;
import io.github.hongcha98.parrot.cloud.ConditionalOnEnableParrotDiscovery;
import io.github.hongcha98.parrot.cloud.ParrotDiscoveryProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnEnableParrotDiscovery
public class ParrotDiscoveryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DiscoveryClient discoveryClient(ParrotDiscoveryProperties parrotDiscoveryProperties, NamingServer namingServer) {
        return parrotDiscoveryProperties.isFailureToleranceEnabled() ? new CacheParrotDiscoveryClient(namingServer) : new ParrotDiscoveryClient(namingServer);
    }

}
