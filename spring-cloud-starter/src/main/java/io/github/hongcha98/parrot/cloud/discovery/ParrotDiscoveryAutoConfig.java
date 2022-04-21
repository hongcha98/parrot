package io.github.hongcha98.parrot.cloud.discovery;

import io.github.hongcha98.parrot.client.naming.NamingServer;
import io.github.hongcha98.parrot.cloud.EnableParrotDiscovery;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableParrotDiscovery
public class ParrotDiscoveryAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public DiscoveryClient discoveryClient(NamingServer namingServer) {
        return new ParrotDiscoveryClient(namingServer);
    }

}
