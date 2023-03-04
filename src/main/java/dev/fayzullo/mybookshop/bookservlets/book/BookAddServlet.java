package dev.fayzullo.mybookshop.bookservlets.book;

import dev.fayzullo.mybookshop.dao.BookDAO;
import dev.fayzullo.mybookshop.dao.CategoryDao;
import dev.fayzullo.mybookshop.dao.DocumentDAO;
import dev.fayzullo.mybookshop.model.Book;
import dev.fayzullo.mybookshop.model.Document;
import dev.fayzullo.mybookshop.utils.Resizer;
import dev.fayzullo.mybookshop.utils.StringUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;

@WebServlet(name = "BookAddServlet", urlPatterns = "/addbook")
@MultipartConfig(location = "D:/files")
public class BookAddServlet extends HttpServlet {

    private static final Path rootPath = Path.of(System.getProperty("user.home"), "/apps/library/upload");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/create.jsp");
        response.setContentType("text/html");
        CategoryDao categoryDao = CategoryDao.getInstance();
        request.setAttribute("categories", categoryDao.findAll());
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String publishedAt1 = request.getParameter("publishedAt");
        System.out.println(publishedAt1);
        LocalDate publishedAt = LocalDate.parse(publishedAt1);
        Part file = request.getPart("file");
        Part image = request.getPart("image");
        String description = request.getParameter("description");
        String publisher = request.getParameter("publisher");
        String categoryId = request.getParameter("category_id");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String url = request.getParameter("url");
        int pages =Integer.parseInt(request.getParameter("pages"));
        Document fileDocument = saveFile(file);
        Document imageDocument = saveCover(image);

        BookDAO bookDAO = BookDAO.getInstance();
        Book book = Book.builder()
                .title(title)
                .author(author)
                .description(description)
                .publisher(publisher)
                .publishedAt(publishedAt)
                .coverId(imageDocument.getId())
                .documentId(fileDocument.getId())
                .category(Integer.parseInt(categoryId))
                .pages(pages)
                .url(url)
                .build();
        bookDAO.save(book);
        response.getWriter().println("   \n" +
                "<h2 style=\"color: #0a58ca\">Muvaffaqiyatli saqlandi</h2>");
    }

    public Document saveCover(Part part) throws RuntimeException {

        try {
            DocumentDAO documentDAO = DocumentDAO.getInstance();
            String generateUniqueName = StringUtils.generateUniqueName(part);
            String coverPath = rootPath.resolve(generateUniqueName).toString();
//            byte[] bytes = ImageUtils.resizeForCover(part.getInputStream(), coverPath);
            String originalName = part.getSubmittedFileName();
            String extension = StringUtils.getFileExtension(originalName);
            String mimeType = part.getContentType();
            Document document = Document.builder()
                    .generatedFileName(generateUniqueName)
                    .originalFileName(originalName)
                    .fileSize(part.getSize())
                    .mimeType(mimeType)
                    .filePath(rootPath.resolve(generateUniqueName).toString())
                    .extension(extension)
                    .build();
            document = documentDAO.save(document);

            OutputStream outputStream = new FileOutputStream(coverPath);
            InputStream input = Resizer.resizeImage(part.getInputStream(), StringUtils.getFileExtension(part), 150, 200);
            outputStream.write(input.readAllBytes());
            return document;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document saveFile(Part file) {
        try {
            DocumentDAO documentDAO = DocumentDAO.getInstance();
            String originalName = file.getSubmittedFileName();
            String extension = StringUtils.getFileExtension(originalName);
            String generatedName = StringUtils.generateUniqueName(file);
            String mimeType = file.getContentType();
            long size = file.getSize();
            Document document = Document.builder()
                    .generatedFileName(generatedName)
                    .originalFileName(originalName)
                    .fileSize(size)
                    .mimeType(mimeType)
                    .filePath(rootPath.resolve(generatedName).toString())
                    .extension(extension)
                    .build();
            document = documentDAO.save(document);
            file.write(generatedName);
            return document;
        } catch (Exception e) {
            // TODO: 08/02/23 redirect to error page
            throw new RuntimeException(e);
        }
    }

}
