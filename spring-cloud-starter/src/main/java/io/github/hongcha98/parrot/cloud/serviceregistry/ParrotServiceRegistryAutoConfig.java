package io.github.hongcha98.parrot.cloud.serviceregistry;

import io.github.hongcha98.parrot.client.naming.DefaultNamingServerServer;
import io.github.hongcha98.parrot.client.naming.NamingServer;
import io.github.hongcha98.parrot.cloud.EnableParrotDiscovery;
import io.github.hongcha98.parrot.cloud.ParrotDiscoveryProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableParrotDiscovery
public class ParrotServiceRegistryAutoConfig {

    @Bean
    public ParrotDiscoveryProperties parrotRegistrationProperties() {
        return new ParrotDiscoveryProperties();
    }

    @Bean
    public NamingServer naming(ParrotDiscoveryProperties parrotDiscoveryProperties) {
        return new DefaultNamingServerServer(parrotDiscoveryProperties.getServerAddr(), parrotDiscoveryProperties.getNamespace());
    }

    @Bean
    @ConditionalOnMissingBean
    public ServiceRegistry<Registration> serviceRegistry(NamingServer namingServer) {
        return new ParrotServiceRegistry(namingServer);
    }

    @Bean
    @ConditionalOnMissingBean
    public AutoServiceRegistration autoServiceRegistration(ServiceRegistry<Registration> serviceRegistry,
                                                           AutoServiceRegistrationProperties autoServiceRegistrationProperties,
                                                           ParrotDiscoveryProperties parrotDiscoveryProperties
    ) {
        return new ParrotAutoServiceRegistration(serviceRegistry, autoServiceRegistrationProperties, parrotDiscoveryProperties);
    }
}
