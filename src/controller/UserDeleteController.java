package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mysql.UserDaoImpl;
import service.ServiceException;
import service.logic.UserServiceImpl;
import util.Connector;

public class UserDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            Connection connection = null;
            try {
                connection = Connector.getConnection();
                UserDaoImpl dao = new UserDaoImpl();
                dao.setConnection(connection);
                UserServiceImpl service = new UserServiceImpl();
                service.setUserDao(dao);
                service.delete(id);
            } catch(SQLException | ServiceException e) {
                throw new ServletException(e);
            } finally {
                try { connection.close(); } catch(Exception e) {}
            }
        }
        resp.sendRedirect(req.getContextPath() + "/user/list.html");
    }
}
