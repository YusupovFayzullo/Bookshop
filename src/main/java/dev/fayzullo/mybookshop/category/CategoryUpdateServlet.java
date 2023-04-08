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

@WebServlet(name = "CategoryUpdate",urlPatterns = "/category/update/*")

public class CategoryUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        CategoryDao categoryDao = CategoryDao.getInstance();
        long categoryId = Long.parseLong(pathInfo.substring(1));
        Category dao = categoryDao.findById(categoryId);
        request.setAttribute("category", dao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/updatecategory.jsp");
        dispatcher.forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newName = req.getParameter("name");

        if(newName==null || newName.isBlank()){
            resp.getWriter().println("<h2 style=\"color: red; text-align: center\"> bo'sh bo'lishi mumkin emas</h2>");
            return;
        }
        long id = Long.parseLong(req.getParameter("id"));
        CategoryDao categoryDao = CategoryDao.getInstance();
        categoryDao.update(new Category(id,newName));
        resp.sendRedirect("/category");
    }
}

