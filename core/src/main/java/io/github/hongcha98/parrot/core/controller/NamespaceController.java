package io.github.hongcha98.parrot.core.controller;

import io.github.hongcha98.parrot.common.constant.URIConstant;
import io.github.hongcha98.parrot.core.manage.ParrotManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(URIConstant.NAMESPACE)
public class NamespaceController {
    @Autowired
    ParrotManage parrotManage;


    @GetMapping
    public Set<String> list() {
        return parrotManage.getNamespaces();
    }

    @PostMapping
    public boolean add(String namespace) {
        parrotManage.addNamespace(namespace);
        return true;
    }

    @DeleteMapping
    public boolean delete(String namespace) {
        parrotManage.deleteNamespace(namespace);
        return true;
    }

}
