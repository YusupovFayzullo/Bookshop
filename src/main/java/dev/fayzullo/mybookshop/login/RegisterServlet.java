package dev.fayzullo.mybookshop.login;

import dev.fayzullo.mybookshop.dao.UserDao;
import dev.fayzullo.mybookshop.model.User;
import dev.fayzullo.mybookshop.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login/register.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        UserDao userDao = UserDao.getInstance();
        if(userDao.checkEmail(email)){
            response.getWriter().println("<h2 style=\"color: red; text-align: center\">"+email+" email allaqachon mavjud</h2>");
            return;
        }
        String password = request.getParameter("password");
        String pre_password = request.getParameter("pre-password");
        if (!password.equalsIgnoreCase(pre_password)) {
            response.getWriter().println("<h2 style=\"color: red; text-align: center\">Parol tasdiqi noto'g'ri</h2>");
        } else {
            String message = UserService.message(new User(firstname, lastname, email, password), pre_password);

            if (message.equalsIgnoreCase("ok")) {

                userDao.save(new User(firstname, lastname, email, password));
                response.getWriter().println(" <h2 style=\"color: blue; text-align: center\"> Muvaffaqiyatli ro'yxatdan o'tdingiz </h2>\n" +
                        "        <a href=\"/books\"> <button type=\"button\" style=\"padding: 15px 32px; color: white; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; border: none; background-color: blue; margin-left: 450px\">Login</button> </a>");
            }   else{
                response.getWriter().println("<h2 style=\"color: red; text-align: center\">" + message + "</h2>");

            }
        }
    }
}
