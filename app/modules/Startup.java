package modules;
import service.ServiceShutdowner;

import com.google.inject.AbstractModule;

public class Startup extends AbstractModule {
    @Override
    protected void configure() {
        bind(ServiceShutdowner.class).asEagerSingleton();
    }
}
