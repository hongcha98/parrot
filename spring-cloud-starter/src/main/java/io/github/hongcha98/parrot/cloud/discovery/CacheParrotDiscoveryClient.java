package io.github.hongcha98.parrot.cloud.discovery;

import io.github.hongcha98.parrot.client.naming.NamingServer;
import org.springframework.cloud.client.ServiceInstance;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CacheParrotDiscoveryClient extends ParrotDiscoveryClient {
    private List<String> services = Collections.emptyList();

    private Map<String, List<ServiceInstance>> instancesMap = new ConcurrentHashMap<>();

    public CacheParrotDiscoveryClient(NamingServer namingServer) {
        super(namingServer);
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        try {
            List<ServiceInstance> instances = super.getInstances(serviceId);
            instancesMap.put(serviceId, instances);
            return instances;
        } catch (Exception e) {
            return instancesMap.getOrDefault(serviceId, Collections.emptyList());
        }
    }

    @Override
    public List<String> getServices() {
        try {
            List<String> services = super.getServices();
            this.services = services;
            return services;
        } catch (Exception e) {
            return services;
        }
    }
}
