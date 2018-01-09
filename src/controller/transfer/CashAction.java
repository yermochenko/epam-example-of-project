package controller.transfer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.User;
import service.ServiceException;
import service.TransferService;
import util.FactoryException;

public class CashAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        Long amount = null;
        try {
            amount = Long.parseLong(req.getParameter("amount"));
        } catch(NumberFormatException e) {}
        String operation = req.getParameter("operation");
        if(id != null && operation != null && amount != null && amount > 0) {
            try {
                TransferService service = getServiceFactory().getTransferService();
                User operator = (User)req.getSession(false).getAttribute("currentUser");
                switch(operation) {
                case "give":
                    service.transfer(id, null, amount, operator);
                    break;
                case "accept":
                    service.transfer(null, id, amount, operator);
                    break;
                }
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        if(id != null) {
            return new Forward("/account/view.html?id="+id);
        } else {
            return new Forward("/account/list.html");
        }
    }
}
