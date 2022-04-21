package io.github.hongcha98.parrot.core.controller;

import io.github.hongcha98.parrot.core.manage.ParrotManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/service")
public class  ServiceController {
    @Autowired
    ParrotManage parrotManage;

    @GetMapping("/{namespace}")
    public Set<String> list(@PathVariable("namespace") String namespace) {
        return parrotManage.getServices(namespace);
    }

}
