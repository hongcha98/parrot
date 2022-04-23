package io.github.hongcha98.parrot.cloud.discovery.reactive;

import io.github.hongcha98.parrot.cloud.discovery.ParrotDiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ParrotReactiveDiscoveryClient implements ReactiveDiscoveryClient {
    public final ParrotDiscoveryClient parrotDiscoveryClient;

    public ParrotReactiveDiscoveryClient(ParrotDiscoveryClient parrotDiscoveryClient) {
        this.parrotDiscoveryClient = parrotDiscoveryClient;
    }

    @Override
    public String description() {
        return "parrot reactive discovery client";
    }

    @Override
    public Flux<ServiceInstance> getInstances(String serviceId) {
        return Mono.justOrEmpty(parrotDiscoveryClient.getInstances(serviceId)).flatMapMany(serviceInstances -> Flux.fromIterable(serviceInstances));
    }

    @Override
    public Flux<String> getServices() {
        return Mono.justOrEmpty(parrotDiscoveryClient.getServices()).flatMapMany(services -> Flux.fromIterable(services));
    }

}
