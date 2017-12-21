package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mysql.UserDaoImpl;
import domain.User;
import service.ServiceException;
import service.logic.UserServiceImpl;
import util.Connector;

public class UserListController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            Connector.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/epam_project_db?useUnicode=true&characterEncoding=UTF-8", "root", "root");
        } catch(ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            UserDaoImpl dao = new UserDaoImpl();
            dao.setConnection(connection);
            UserServiceImpl service = new UserServiceImpl();
            service.setUserDao(dao);
            List<User> users = service.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/user/list.html").forward(req, resp);
        } catch(SQLException | ServiceException e) {
            throw new ServletException(e);
        } finally {
            try { connection.close(); } catch(Exception e) {}
        }
    }
}
