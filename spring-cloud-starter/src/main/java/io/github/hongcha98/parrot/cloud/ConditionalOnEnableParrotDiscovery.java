package io.github.hongcha98.parrot.cloud;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@ConditionalOnDiscoveryEnabled
@ConditionalOnProperty(value = "spring.cloud.parrot.discovery.enable", matchIfMissing = true)
public @interface ConditionalOnEnableParrotDiscovery {
}
