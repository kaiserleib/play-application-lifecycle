package service;

import com.google.inject.ImplementedBy;
import service.impl.ShutdownableServiceImpl;

@ImplementedBy(ShutdownableServiceImpl.class)
public interface ShutdownableService {
    void start();
    void stop();
    boolean isStarted();
}