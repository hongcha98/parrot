package io.github.hongcha98.parrot.client.naming;

import io.github.hongcha98.parrot.client.constant.URIConstant;
import io.github.hongcha98.parrot.client.http.HttpMethod;
import io.github.hongcha98.parrot.client.http.HttpUtils;
import io.github.hongcha98.parrot.client.model.Instance;

import java.util.*;

public class DefaultNamingServerServer implements NamingServer {
    private String serverAddr;
    private String namespace;

    public DefaultNamingServerServer() {
        this(null, null);
    }

    public DefaultNamingServerServer(String serverAddr, String namespace) {
        this.serverAddr = "http://" + Objects.toString(serverAddr, "127.0.0.1:7654");
        this.namespace = Objects.toString(namespace, "PUBLIC");
    }


    @Override
    public void registry(Instance instance) {
        HttpUtils.reqApi(HttpMethod.POST, serverAddr + URIConstant.INSTANCE + "/" + namespace, null, instance, boolean.class);
    }

    @Override
    public void unRegistry(Instance instance) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", instance.getIp());
        params.put("port", String.valueOf(instance.getPort()));
        params.put("serviceName", instance.getServiceName());
        HttpUtils.reqApi(HttpMethod.DELETE, serverAddr + URIConstant.INSTANCE + "/" + namespace, params, null, boolean.class);
    }

    @Override
    public List<Instance> getInstances(String service) {
        return Collections.unmodifiableList(
                HttpUtils.reqApiListResp(HttpMethod.GET, serverAddr + URIConstant.INSTANCE + "/" + namespace + "/" + service, null, null, Instance.class)
        );
    }

    @Override
    public List<String> getServices() {
        return Collections.unmodifiableList(
                HttpUtils.reqApiListResp(HttpMethod.GET, serverAddr + URIConstant.SERVICE + "/" + namespace, null, null, String.class)
        );
    }
}
