package service.impl;

import com.google.inject.Inject;

import javax.inject.Named;




import com.google.inject.Singleton;
import service.ShutdownableService;
import service.ServiceShutdowner;

@Singleton
@Named
public class ShutdownableServiceImpl implements ShutdownableService, Runnable {

    private volatile boolean started;
    private Thread thread;

    @Inject private ServiceShutdowner serviceShutdowner;

    public ShutdownableServiceImpl() {
        System.out.println("constructor called");
        this.started = false;
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
