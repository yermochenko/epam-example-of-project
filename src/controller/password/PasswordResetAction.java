package controller.password;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.User;
import service.ServiceException;
import service.UserService;
import util.FactoryException;

public class PasswordResetAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService service = getServiceFactory().getUserService();
            User user = service.findById(Long.parseLong(req.getParameter("id")));
            if(user != null) {
                service.changePassword(user.getId(), user.getPassword(), null);
            }
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        } catch(NumberFormatException e) {}
        return new Forward("/user/list.html");
    }
}
