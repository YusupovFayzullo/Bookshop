package dev.fayzullo.mybookshop.category;


import com.google.gson.Gson;
import dev.fayzullo.mybookshop.dao.CategoryDao;
import dev.fayzullo.mybookshop.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CategoryGetServlet", urlPatterns = "/category/get/*")
public class CategoryGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long categoryId = Long.parseLong(request.getPathInfo().substring(1));
        Category category = CategoryDao.getInstance().findById(categoryId);
        Gson gson = new Gson();
        String jsonDATA = gson.toJson(category);
        response.setContentType("application/json");
        response.getWriter().println(jsonDATA);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
