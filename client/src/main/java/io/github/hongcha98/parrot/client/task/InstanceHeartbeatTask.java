package io.github.hongcha98.parrot.client.task;

import io.github.hongcha98.parrot.client.naming.NamingServer;
import io.github.hongcha98.parrot.common.model.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InstanceHeartbeatTask {
    private static final Logger LOG = LoggerFactory.getLogger(InstanceHeartbeatTask.class);

    private final ScheduledExecutorService scheduledExecutorService;

    private final Set<Instance> instances;

    private final NamingServer namingServer;

    public InstanceHeartbeatTask(long taskTime, NamingServer namingServer) {
        this.namingServer = namingServer;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        this.instances = new CopyOnWriteArraySet<>();
        scheduledExecutorService.scheduleAtFixedRate(() -> instanceHeartbeat(), taskTime, taskTime, TimeUnit.MILLISECONDS);
    }

    public void addInstance(Instance instance) {
        instances.add(instance);
    }

    public void removeInstance(Instance instance) {
        instances.remove(instance);
    }


    private void instanceHeartbeat() {
        instances.parallelStream().forEach(instance -> {
            try {
                namingServer.registry(instance);
            } catch (Exception e) {
                LOG.error(instance + " , registry error", e);
            }
        });
    }

    public void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
