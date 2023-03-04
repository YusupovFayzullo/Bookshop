package dev.fayzullo.mybookshop.bookservlets.book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "BookGetServlet", urlPatterns = "/book/get/*")
public class BookGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getPathInfo().substring(1);
        response.sendError(404, "Book With id '%s' not found".formatted(bookId));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


}
