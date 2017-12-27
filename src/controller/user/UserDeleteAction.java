package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import service.ServiceException;
import service.UserService;
import util.FactoryException;

public class UserDeleteAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                service.delete(id);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}
