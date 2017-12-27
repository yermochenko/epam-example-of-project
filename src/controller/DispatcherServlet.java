package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Connector;
import util.MainServiceFactoryImpl;
import util.ServiceFactory;

public class DispatcherServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            Connector.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/epam_project_db?useUnicode=true&characterEncoding=UTF-8", "root", "root");
        } catch(ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public ServiceFactory getServiceFactory() {
        return new MainServiceFactoryImpl();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Action action = ActionFactory.getAction(url);
        try(ServiceFactory factory = getServiceFactory()) {
            action.setServiceFactory(factory);
            Forward forward = action.execute(req, resp);
            if(forward != null && forward.isRedirect()) {
                resp.sendRedirect(context + forward.getUrl());
            } else {
                if(forward != null && forward.getUrl() != null) {
                    url = forward.getUrl();
                }
                req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
            }
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
