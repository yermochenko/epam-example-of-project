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

public class TransferAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long sourceId = null;
        try {
            sourceId = Long.parseLong(req.getParameter("source"));
        } catch(NumberFormatException e) {}
        Long destinationId = null;
        try {
            destinationId = Long.parseLong(req.getParameter("destination"));
        } catch(NumberFormatException e) {}
        Long amount = null;
        try {
            amount = Long.parseLong(req.getParameter("amount"));
        } catch(NumberFormatException e) {}
        if(sourceId != null && destinationId != null && amount != null && amount > 0) {
            try {
                TransferService service = getServiceFactory().getTransferService();
                service.transfer(sourceId, destinationId, amount, (User)req.getSession(false).getAttribute("currentUser"));
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/account/list.html");
    }
}
