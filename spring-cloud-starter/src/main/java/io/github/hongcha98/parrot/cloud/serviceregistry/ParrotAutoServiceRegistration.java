package io.github.hongcha98.parrot.cloud.serviceregistry;


import io.github.hongcha98.parrot.cloud.ParrotDiscoveryProperties;
import io.github.hongcha98.parrot.cloud.ParrotServiceInstance;
import io.github.hongcha98.parrot.common.model.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

public class ParrotAutoServiceRegistration extends AbstractAutoServiceRegistration<Registration> {
    private static final Logger LOG = LoggerFactory.getLogger(ParrotAutoServiceRegistration.class);

    private ParrotDiscoveryProperties parrotDiscoveryProperties;

    private AutoServiceRegistrationProperties autoServiceRegistrationProperties;

    private Registration registration = null;

    public ParrotAutoServiceRegistration(ServiceRegistry serviceRegistry, AutoServiceRegistrationProperties autoServiceRegistrationProperties, ParrotDiscoveryProperties parrotDiscoveryProperties) {
        super(serviceRegistry, autoServiceRegistrationProperties);
        this.parrotDiscoveryProperties = parrotDiscoveryProperties;
        this.autoServiceRegistrationProperties = autoServiceRegistrationProperties;
    }

    @Override
    protected Object getConfiguration() {
        return null;
    }

    @Override
    protected boolean isEnabled() {
        return parrotDiscoveryProperties.isEnable();
    }

    @Override
    protected Registration getRegistration() {
        if (registration == null) {
            synchronized (this) {
                if (registration == null) {
                    Instance instance = new Instance();
                    String ip = parrotDiscoveryProperties.getIp();
                    instance.setIp(ip);
                    Integer port = parrotDiscoveryProperties.getPort();
                    instance.setPort(port != null ? port : getPort().get());
                    instance.setMetaData(parrotDiscoveryProperties.getMetadata());
                    instance.setServiceName(parrotDiscoveryProperties.getServiceName());
                    registration = new ParrotServiceInstance(instance);
                }
            }
        }
        return registration;
    }

    @Override
    public void start() {
        super.start();
        LOG.info("parrot registration info : {}", registration);
    }

    @Override
    protected Registration getManagementRegistration() {
        return null;
    }


}
