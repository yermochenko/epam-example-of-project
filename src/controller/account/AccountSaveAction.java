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

public class AccountSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = new Account();
        try {
            account.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        account.setClient(req.getParameter("client"));
        if(account.getClient() != null) {
            try {
                AccountService service = getServiceFactory().getAccountService();
                service.save(account);
                return new Forward("/account/view.html?id="+account.getId());
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/account/list.html");
    }
}
