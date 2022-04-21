package io.github.hongcha98.parrot.cloud.serviceregistry;

import io.github.hongcha98.parrot.client.naming.NamingServer;
import io.github.hongcha98.parrot.common.model.Instance;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

public class ParrotServiceRegistry implements ServiceRegistry<Registration> {
    private final NamingServer namingServer;

    public ParrotServiceRegistry(NamingServer namingServer) {
        this.namingServer = namingServer;
    }


    @Override
    public void register(Registration registration) {
        namingServer.registry(buildInstance(registration));
    }

    @Override
    public void deregister(Registration registration) {
        namingServer.deregister(buildInstance(registration));
    }

    @Override
    public void close() {
        namingServer.shutdown();
    }

    @Override
    public void setStatus(Registration registration, String status) {
        // TODO
    }

    @Override
    public <T> T getStatus(Registration registration) {
        // TODO
        return null;
    }

    private Instance buildInstance(Registration registration) {
        Instance instance = new Instance();
        instance.setIp(registration.getHost());
        instance.setPort(registration.getPort());
        instance.setMetaData(registration.getMetadata());
        instance.setServiceName(registration.getServiceId());
        return instance;
    }


}
