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


@WebServlet(name = "DeleteCategoryServlet", urlPatterns = "/category/delete/*")
public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        CategoryDao categoryDao = CategoryDao.getInstance();
        long categoryId = Long.parseLong(pathInfo.substring(1));
        Category categoryDaoById = categoryDao.findById(categoryId);
        request.setAttribute("category", categoryDaoById);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/deletecategory.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao categoryDao = CategoryDao.getInstance();
        categoryDao.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/category");
    }
}
