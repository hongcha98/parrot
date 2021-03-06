package io.github.hongcha98.parrot.common.model;


import java.util.Map;
import java.util.Objects;

public class Instance {
    private String ip;
    private int port;
    private Map<String, String> metaData;
    private long heartbeatTime;
    private String serviceName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", metaData=" + metaData +
                ", heartbeatTime=" + heartbeatTime +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

    public long getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(long heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instance instance = (Instance) o;
        return port == instance.port && Objects.equals(ip, instance.ip) && Objects.equals(serviceName, instance.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port, serviceName);
    }
}
