package controller.account;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.Account;
import service.AccountService;
import service.ServiceException;
import util.FactoryException;

public class AccountListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AccountService service = getServiceFactory().getAccountService();
            List<Account> accounts = service.findAll();
            req.setAttribute("accounts", accounts);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
