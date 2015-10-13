package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import service.ServiceShutdowner;

import com.google.inject.Inject;
import service.ShutdownableService;

public class Application extends Controller {
    @Inject private ShutdownableService service;

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result isServiceStarted() {
        return service.isStarted() ? ok("service is started") : ok("no, stopped is service");
    }

    public Result startService() {
        service.start();
        return ok("started");
    }

    public Result stopService() {
        service.stop();
        return ok("stop");
    }
}
