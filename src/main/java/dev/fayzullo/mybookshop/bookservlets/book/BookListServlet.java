package dev.fayzullo.mybookshop.bookservlets.book;

import dev.fayzullo.mybookshop.dao.BookDAO;
import dev.fayzullo.mybookshop.model.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "BookListServlet",  value = "/booklist")

@MultipartConfig(location = "C:/Users/User/apps/library/upload")
public class BookListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDAO = BookDAO.getInstance();
        request.setAttribute("books", bookDAO.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/books/b.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(405, "Method Not Supported");
    }
}
