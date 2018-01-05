package controller.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.Account;
import service.AccountService;
import service.ServiceException;
import util.FactoryException;

public class AccountEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try {
                AccountService service = getServiceFactory().getAccountService();
                Account account = service.findById(id);
                req.setAttribute("account", account);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
