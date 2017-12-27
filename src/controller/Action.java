package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ServiceFactory;

abstract public class Action {
    private ServiceFactory serviceFactory;

    public final ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    public final void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    abstract public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
