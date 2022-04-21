package io.github.hongcha98.parrot.cloud.discovery;

import io.github.hongcha98.parrot.client.naming.NamingServer;
import io.github.hongcha98.parrot.cloud.ParrotServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;
import java.util.stream.Collectors;


public class ParrotDiscoveryClient implements DiscoveryClient {
    private final NamingServer namingServer;

    public ParrotDiscoveryClient(NamingServer namingServer) {
        this.namingServer = namingServer;
    }


    @Override
    public String description() {
        return "parrot";
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        return namingServer.getInstances(serviceId).stream().map(ParrotServiceInstance::new).collect(Collectors.toList());
    }

    @Override
    public List<String> getServices() {
        return namingServer.getServices();
    }
}
