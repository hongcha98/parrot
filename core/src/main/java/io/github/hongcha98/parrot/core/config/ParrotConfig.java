package io.github.hongcha98.parrot.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("parrot")
public class ParrotConfig {
    private long instanceHeartbeatTaskTime = 1000;

    private long heartbeatTimeout = 15000;

    public long getInstanceHeartbeatTaskTime() {
        return instanceHeartbeatTaskTime;
    }

    public void setInstanceHeartbeatTaskTime(long instanceHeartbeatTaskTime) {
        this.instanceHeartbeatTaskTime = instanceHeartbeatTaskTime;
    }

    public long getHeartbeatTimeout() {
        return heartbeatTimeout;
    }

    public void setHeartbeatTimeout(long heartbeatTimeout) {
        this.heartbeatTimeout = heartbeatTimeout;
    }
}
