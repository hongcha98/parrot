package io.github.hongcha98.parrot.core.task;

import io.github.hongcha98.parrot.common.model.Instance;
import io.github.hongcha98.parrot.core.config.ParrotConfig;
import io.github.hongcha98.parrot.core.manage.ParrotManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Component
public class InstanceHeartbeatTask {
    private static final Logger LOG = LoggerFactory.getLogger(InstanceHeartbeatTask.class);
    private final ParrotManage parrotManage;
    private final ParrotConfig parrotConfig;
    private ScheduledExecutorService scheduledExecutorService;

    public InstanceHeartbeatTask(ParrotManage parrotManage, ParrotConfig parrotConfig) {
        this.parrotManage = parrotManage;
        this.parrotConfig = parrotConfig;

    }


    @PostConstruct
    public void init() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> serviceHeartbeat(), parrotConfig.getInstanceHeartbeatTaskTime(), parrotConfig.getInstanceHeartbeatTaskTime(), TimeUnit.MILLISECONDS);
    }

    @PreDestroy
    public void destroy() {
        scheduledExecutorService.shutdown();
    }

    private void serviceHeartbeat() {
        try {
            long heartbeatTimeout = parrotConfig.getHeartbeatTimeout();
            parrotManage.getNamespaces().parallelStream().forEach(namespace -> {
                Set<String> services = parrotManage.getServices(namespace);
                services.parallelStream().forEach(service -> {
                    Set<Instance> instances = parrotManage.getInstance(namespace, service);
                    List<Instance> timeOut = instances.stream().filter(instance -> System.currentTimeMillis() - instance.getHeartbeatTime() > heartbeatTimeout).collect(Collectors.toList());
                    if (!timeOut.isEmpty()) {
                        LOG.info("namespace : {} ,service :{} , timeout instances : {}", namespace, service, timeOut);
                        instances.removeAll(timeOut);
                    }
                    if (instances.isEmpty()) {
                        parrotManage.deleteService(namespace, service);
                    }
                });
            });
        } catch (Exception e) {
            LOG.error("InstanceHeartbeatTask error", e);
        }

    }
}
