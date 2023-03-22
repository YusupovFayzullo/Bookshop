package dev.fayzullo.mybookshop.bookservlets.book;

import dev.fayzullo.mybookshop.dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "BookSearchServlet", value = "/search")
public class BookSearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        int category_id = Integer.parseInt(req.getParameter("category_id"));
        BookDAO bookDAO = BookDAO.getInstance();
//        if(category_id==BookServlet.all2.size()){
//
//            bookDAO.getByName(search,0);
//        }
//        else {
//            bookDAO.getByName(search,category_id);
//        }
    }
}
