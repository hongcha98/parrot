package io.github.hongcha98.parrot.cloud;


import io.github.hongcha98.parrot.common.model.Instance;
import org.springframework.cloud.client.serviceregistry.Registration;

import java.net.URI;
import java.util.Map;

public class ParrotServiceInstance implements Registration {
    private final Instance instance;


    public ParrotServiceInstance(Instance instance) {
        this.instance = instance;
    }


    @Override
    public String getServiceId() {
        return instance.getServiceName();
    }

    @Override
    public String getHost() {
        return instance.getIp();
    }

    @Override
    public int getPort() {
        return instance.getPort();
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public URI getUri() {
        return URI.create("http://" + getHost() + ":" + getPort());
    }

    @Override
    public Map<String, String> getMetadata() {
        return instance.getMetaData();
    }

    @Override
    public String toString() {
        return "ParrotServiceInstance{" +
                "instance=" + instance +
                '}';
    }
}
