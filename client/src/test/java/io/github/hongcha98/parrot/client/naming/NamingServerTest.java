package io.github.hongcha98.parrot.client.naming;

import io.github.hongcha98.parrot.client.model.Instance;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class NamingServerTest {
    NamingServer namingServer;

    Instance instance;

    @Before
    public void init() {
        namingServer = new DefaultNamingServerServer();
        instance = new Instance();
        instance.setIp("127.0.0.1");
        instance.setPort(7890);
        instance.setMetaData(new HashMap<String, String>());
        instance.setServiceName("test-server");
    }

    @Test
    public void registry() {
        namingServer.registry(instance);
    }

    @Test
    public void unRegistry() {
        namingServer.unRegistry(instance);
    }

    @Test
    public void getInstances() {
        System.out.println("naming.getInstances(\"test-server\") = " + namingServer.getInstances("test-server"));
    }

    @Test
    public void getServices() {
        System.out.println("naming.getServices() = " + namingServer.getServices());
    }
}
