package service.impl;
import com.google.inject.Inject;
import service.ServiceShutdowner;
import service.ShutdownableService;
import com.google.inject.Singleton;

import play.inject.ApplicationLifecycle;
import play.libs.F;

import javax.inject.Named;

@Singleton
@Named
public class ServiceShutdownerImpl implements ServiceShutdowner{

    @Inject private ShutdownableService service;

    @Inject
    public ServiceShutdownerImpl(ApplicationLifecycle lifecycle) {
        lifecycle.addStopHook(() -> {
            service.stop();
            return F.Promise.pure(null);
        });
    }
}
