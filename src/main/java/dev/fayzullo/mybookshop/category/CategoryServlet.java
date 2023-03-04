package dev.fayzullo.mybookshop.category;


import dev.fayzullo.mybookshop.dao.CategoryDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;


@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao categoryDao = CategoryDao.getInstance();
        short page = Short.parseShort(Objects.requireNonNullElse(request.getParameter("page"), "0"));
        short size = Short.parseShort(Objects.requireNonNullElse(request.getParameter("size"), "3"));
        long totalCount = categoryDao.totalCount();
        long pageCount = totalCount / size;
        long currentPage = page;
        request.setAttribute("categorys", categoryDao.findAll(page, size));
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("hasPrevious", currentPage > 0);
        request.setAttribute("hasNext", currentPage < pageCount);
        request.setAttribute("previous", page - 1);
        request.setAttribute("next", page + 1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/category.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
