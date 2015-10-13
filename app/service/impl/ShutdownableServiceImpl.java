package service.impl;
import com.google.inject.Inject;

import javax.inject.Named;

import play.inject.ApplicationLifecycle;
import play.libs.F;


import com.google.inject.Singleton;
import service.ShutdownableService;

@Singleton
@Named
public class ShutdownableServiceImpl implements ShutdownableService, Runnable {
    private volatile boolean started;
    private Thread thread;

    @Inject
    public ShutdownableServiceImpl(ApplicationLifecycle lifecycle) {
        System.out.println("constructor called");
        this.started = false;

        lifecycle.addStopHook(() -> {
            started = false;
            return F.Promise.pure(null);
        });
    }

    @Override
    public void start() {
        System.out.println("start() called");
        if (!started) {
            thread = new Thread(this, "steve: " + System.currentTimeMillis());
            thread.start();
            this.started = true;
        }
    }

    @Override
    public void stop() {
        System.out.println("stop() called");
        if (started) {
            started = false;
            thread = null;
        }
    }

    @Override
    public boolean isStarted() {
        System.out.println("isStarted() called");
        return this.started;
    }

    @Override
    public void run() {
        while (started) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {}
            System.out.println(Thread.currentThread().getName() + ": I am here!");
        }
        System.out.println(Thread.currentThread().getName() + " Goodbye Nate");
    }
}
