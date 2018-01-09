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

public class PasswordSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("old-password");
        String newPassword = req.getParameter("new-password");
        String newPasswordRepeat = req.getParameter("new-password-repeat");
        if(oldPassword != null && newPassword != null && newPasswordRepeat != null && newPassword.equals(newPasswordRepeat)) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = (User)req.getSession(false).getAttribute("currentUser");
                service.changePassword(user.getId(), oldPassword, newPassword);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/index.html");
    }
}
