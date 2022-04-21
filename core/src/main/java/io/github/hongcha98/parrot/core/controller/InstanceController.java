package io.github.hongcha98.parrot.core.controller;

import io.github.hongcha98.parrot.core.manage.ParrotManage;
import io.github.hongcha98.parrot.core.model.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/instance")
public class InstanceController {
    @Autowired
    ParrotManage parrotManage;

    @GetMapping("/{namespace}/{service}")
    public Set<Instance> list(@PathVariable("namespace") String namespace, @PathVariable("service") String service) {
        return parrotManage.getInstance(namespace, service);
    }

    @PostMapping("/{namespace}")
    public boolean add(@PathVariable("namespace") String namespace, @RequestBody Instance instance) {
        instance.setHeartbeatTime(System.currentTimeMillis());
        parrotManage.addInstance(namespace, instance);
        return true;
    }

    @DeleteMapping("/{namespace}")
    public boolean delete(@PathVariable("namespace") String namespace, Instance instance) {
        parrotManage.deleteInstance(namespace, instance);
        return true;
    }

}
