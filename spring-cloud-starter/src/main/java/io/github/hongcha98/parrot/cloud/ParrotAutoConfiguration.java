package io.github.hongcha98.parrot.cloud;

import io.github.hongcha98.parrot.client.naming.DefaultNamingServer;
import io.github.hongcha98.parrot.client.naming.NamingServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnEnableParrotDiscovery
public class ParrotAutoConfiguration {
    @Bean
    public ParrotDiscoveryProperties parrotRegistrationProperties() {
        return new ParrotDiscoveryProperties();
    }

    @Bean
    public NamingServer namingServer(ParrotDiscoveryProperties parrotDiscoveryProperties) {
        return new DefaultNamingServer(parrotDiscoveryProperties.getServerAddr(), parrotDiscoveryProperties.getNamespace());
    }
}
