package dev.fayzullo.mybookshop.bookservlets.user;

import dev.fayzullo.mybookshop.dao.BookDAO;
import dev.fayzullo.mybookshop.dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = UserDao.getInstance();
        req.setAttribute("users", userDao.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/users.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean block = Boolean.parseBoolean(req.getParameter("block"));

    }
}
