package io.github.hongcha98.parrot.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/service")
public class ParrotCloudTest {
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/{service}")
    public List<ServiceInstance> getInstances(@PathVariable("service") String service) {
        return discoveryClient.getInstances(service);
    }

    @GetMapping
    public List<String> getService() {
        return discoveryClient.getServices();
    }

    @GetMapping("/loadBalancer/{service}")
    public ServiceInstance choose(@PathVariable("service") String service) {
        return loadBalancerClient.choose(service);
    }


    public static void main(String[] args) {
        SpringApplication.run(ParrotCloudTest.class, args);
    }
}
