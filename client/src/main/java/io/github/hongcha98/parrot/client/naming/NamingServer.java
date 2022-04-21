package io.github.hongcha98.parrot.client.naming;

import io.github.hongcha98.parrot.client.model.Instance;

import java.util.List;

public interface NamingServer {

    void registry(Instance instance);

    void unRegistry(Instance instance);

    List<Instance> getInstances(String service);

    List<String> getServices();
}
