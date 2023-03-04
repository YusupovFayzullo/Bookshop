package dev.fayzullo.mybookshop.category;

import dev.fayzullo.mybookshop.dao.CategoryDao;
import dev.fayzullo.mybookshop.model.Category;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddCategory",value = "/category/add")
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/books/addc.jsp");
        String name = req.getParameter("name");
        if(name==null || name.isBlank()){
            resp.getWriter().println("<h2 style=\"color: red; text-align: center\"> bo'sh bo'lishi mumkin emas</h2>");
            return;
        }
        CategoryDao categoryDao = CategoryDao.getInstance();
        if (!categoryDao.findByName(name)) {
            categoryDao.save(new Category(name));
              dispatcher.forward(req,resp);
        }else {
            resp.getWriter().println("<h2 style=\"color: red; text-align: center\">"+name+" allaqachon mavjud</h2>");
        }
    }
}
