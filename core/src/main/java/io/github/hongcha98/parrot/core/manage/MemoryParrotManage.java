package io.github.hongcha98.parrot.core.manage;

import io.github.hongcha98.parrot.core.error.ParrotException;
import io.github.hongcha98.parrot.core.model.Instance;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class MemoryParrotManage implements ParrotManage {
    private Map<String, Map<String, Set<Instance>>> infoMap = new ConcurrentHashMap<>();

    public MemoryParrotManage() {
        addNamespace("PUBLIC");
    }

    @Override
    public void addNamespace(String namespace) {
        infoMap.computeIfAbsent(namespace, ns -> new ConcurrentHashMap<>());
    }

    @Override
    public void deleteNamespace(String namespace) {
        infoMap.remove(namespace);
    }

    @Override
    public Set<String> getNamespaces() {
        return infoMap.keySet();
    }

    @Override
    public void addInstance(String namespace, Instance instance) {
        Map<String, Set<Instance>> serviceMap = getServiceMap(namespace);
        String serviceName = instance.getServiceName();
        serviceMap.computeIfAbsent(serviceName, s -> new CopyOnWriteArraySet<>()).add(instance);
    }


    @Override
    public void deleteInstance(String namespace, Instance instance) {
        Map<String, Set<Instance>> serviceMap = getServiceMap(namespace);
        Set<Instance> instances = serviceMap.get(instance.getServiceName());
        if (instances != null) {
            instances.remove(instance);
        }
    }

    @Override
    public Set<Instance> getInstance(String namespace, String service) {
        Map<String, Set<Instance>> serviceMap = getServiceMap(namespace);
        return serviceMap.getOrDefault(service, Collections.emptySet());
    }

    @Override
    public Set<String> getServices(String namespace) {
        Map<String, Set<Instance>> serviceMap = getServiceMap(namespace);
        return serviceMap.keySet();
    }

    @Override
    public void deleteService(String namespace, String service) {
        Map<String, Set<Instance>> serviceMap = getServiceMap(namespace);
        serviceMap.remove(service);
    }

    private Map<String, Set<Instance>> getServiceMap(String namespace) {
        Map<String, Set<Instance>> serviceMap = infoMap.get(namespace);
        if (serviceMap == null) {
            throw new ParrotException("namespace : " + namespace + " does not exist");
        }
        return serviceMap;
    }
}
