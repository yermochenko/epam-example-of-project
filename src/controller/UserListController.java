package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\">");
            writer.println("<title>Список пользователей</title>");
            writer.println("<link href=\"../main.css\" rel=\"stylesheet\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>Банк «Рога и копыта»</h1>");
            writer.println("<h2>Список пользователей</h2>");
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<th>Имя пользователя</th>");
            writer.println("<th>Роль</th>");
            writer.println("<td>&nbsp;</td>");
            writer.println("</tr>");
            for(User user: users) {
                writer.println("<tr>");
                writer.printf("<td class=\"content\">%s</td>\n", user.getLogin());
                writer.printf("<td class=\"content\">%s</td>\n", user.getRole());
                writer.printf("<td class=\"empty\"><a href=\"edit.html?id=%d\" class=\"edit\"></a></td>\n", user.getId());
                writer.println("</tr>");
            }
            writer.println("</table>");
            writer.println("<a href=\"edit.html\" class=\"add-button\">Добавить</a>");
            writer.println("</body>");
            writer.println("</html>");
        } catch(SQLException | ServiceException e) {
            throw new ServletException(e);
        } finally {
            try { connection.close(); } catch(Exception e) {}
        }
    }
}
