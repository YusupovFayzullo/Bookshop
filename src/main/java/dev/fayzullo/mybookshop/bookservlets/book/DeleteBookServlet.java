package dev.fayzullo.mybookshop.bookservlets.book;

import dev.fayzullo.mybookshop.dao.BookDAO;
import dev.fayzullo.mybookshop.dao.CategoryDao;
import dev.fayzullo.mybookshop.model.Book;
import dev.fayzullo.mybookshop.model.Category;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteBookServlet", urlPatterns = "/deleteBook/*")
public class DeleteBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        BookDAO bookDAO = BookDAO.getInstance();
        long bookId = Long.parseLong(pathInfo.substring(1));
        Book book = bookDAO.findById(bookId);
        request.setAttribute("book", book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/deletebook.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDAO = BookDAO.getInstance();
        bookDAO.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/booklist");
    }
}
