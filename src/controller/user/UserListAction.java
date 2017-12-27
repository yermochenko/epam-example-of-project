package controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.User;
import service.ServiceException;
import service.UserService;
import util.FactoryException;

public class UserListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService service = getServiceFactory().getUserService();
            List<User> users = service.findAll();
            req.setAttribute("users", users);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
