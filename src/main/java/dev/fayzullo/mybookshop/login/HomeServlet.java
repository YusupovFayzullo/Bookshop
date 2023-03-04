package dev.fayzullo.mybookshop.login;

import dev.fayzullo.mybookshop.dao.BookDAO;
import dev.fayzullo.mybookshop.dao.UserDao;
import dev.fayzullo.mybookshop.dto.BookDetailsDTO;
import dev.fayzullo.mybookshop.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "Homeservlet",value = "/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher1 = req.getRequestDispatcher("/books/admin.jsp");
        RequestDispatcher dispatcher2 = req.getRequestDispatcher("/books/user.jsp");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDao userDao=new UserDao();
        String s = userDao.loginCheckUser(email, password);

        if(s.equalsIgnoreCase("admin")){
            dispatcher1.forward(req,resp);
        }
        else if(s.equalsIgnoreCase("ok")){
            BookDAO bookDAO = BookDAO.getInstance();
            List<BookDetailsDTO> bookDetailsDTOList=bookDAO.findAll();
            req.setAttribute("books", bookDetailsDTOList);
            dispatcher2.forward(req,resp);
        }
        else{
            resp.getWriter().println("<h1 style=\"color: red; text-align: center\">"+s+"</h1>");
        }
    }
}
