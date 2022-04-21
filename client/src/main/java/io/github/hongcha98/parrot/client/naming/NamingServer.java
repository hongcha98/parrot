package io.github.hongcha98.parrot.client.naming;


import io.github.hongcha98.parrot.common.model.Instance;

import java.util.List;

public interface NamingServer {

    void registry(Instance instance);

    void deregister(Instance instance);

    List<Instance> getInstances(String service);

    List<String> getServices();

    void shutdown();

}
