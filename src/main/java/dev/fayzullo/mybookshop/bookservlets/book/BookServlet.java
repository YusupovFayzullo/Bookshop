package dev.fayzullo.mybookshop.bookservlets.book;

import dev.fayzullo.mybookshop.dao.CategoryDao;
import dev.fayzullo.mybookshop.model.Category;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookServlet",value = "/books")
public class BookServlet extends HttpServlet {
  public  static  List<Category> all2=new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDao categoryDao = CategoryDao.getInstance();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/books/book.jsp");
        List<Category> all = categoryDao.findAll();
        all2.addAll(all);
        all2.add(new Category("Barchasi bo'yicha "));
        req.setAttribute("categories",all2);
        dispatcher.forward(req,resp);

    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
