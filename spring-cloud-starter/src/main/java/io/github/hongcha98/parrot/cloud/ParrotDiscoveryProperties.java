package io.github.hongcha98.parrot.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ConfigurationProperties("spring.cloud.parrot.discovery")
public class ParrotDiscoveryProperties {
    private static final Logger LOG = LoggerFactory.getLogger(ParrotDiscoveryProperties.class);

    private String serverAddr;

    private String namespace;

    @Value("${spring.cloud.parrot.discovery.serviceName:${spring.application.name:}}")
    private String serviceName;

    private String ip;

    private Integer port;

    private Map<String, String> metadata = new HashMap<>();

    private boolean enable = true;

    private boolean failureToleranceEnabled = false;

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isFailureToleranceEnabled() {
        return failureToleranceEnabled;
    }

    public void setFailureToleranceEnabled(boolean failureToleranceEnabled) {
        this.failureToleranceEnabled = failureToleranceEnabled;
    }

    @PostConstruct
    public void init() throws UnknownHostException {
        this.serverAddr = Objects.toString(serverAddr, "127.0.0.1:7654");
        this.namespace = Objects.toString(namespace, "PUBLIC");
        this.ip = Objects.toString(ip, Inet4Address.getLocalHost().getHostAddress());
        Assert.hasText(serviceName, "service name is null");
        LOG.info(this.toString());
    }

    @Override
    public String toString() {
        return "ParrotDiscoveryProperties{" +
                "serverAddr='" + serverAddr + '\'' +
                ", namespace='" + namespace + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", metadata=" + metadata +
                ", enable=" + enable +
                ", failureToleranceEnabled=" + failureToleranceEnabled +
                '}';
    }
}
