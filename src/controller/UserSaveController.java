package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mysql.UserDaoImpl;
import domain.Role;
import domain.User;
import service.ServiceException;
import service.logic.UserServiceImpl;
import util.Connector;

public class UserSaveController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        user.setLogin(req.getParameter("login"));
        try {
            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {}
        if(user.getLogin() != null && user.getRole() != null) {
            Connection connection = null;
            try {
                connection = Connector.getConnection();
                UserDaoImpl dao = new UserDaoImpl();
                dao.setConnection(connection);
                UserServiceImpl service = new UserServiceImpl();
                service.setUserDao(dao);
                service.setDefaultPassword("12345");
                service.save(user);
            } catch(SQLException | ServiceException e) {
                throw new ServletException(e);
            } finally {
                try { connection.close(); } catch(Exception e) {}
            }
        }
        resp.sendRedirect(req.getContextPath() + "/user/list.html");
    }
}
