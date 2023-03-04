package dev.fayzullo.mybookshop.bookservlets.book;

import dev.fayzullo.mybookshop.dao.BookDAO;
import dev.fayzullo.mybookshop.dao.CategoryDao;
import dev.fayzullo.mybookshop.dao.DocumentDAO;
import dev.fayzullo.mybookshop.model.Book;
import dev.fayzullo.mybookshop.model.Category;
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
import org.postgresql.ds.PGSimpleDataSource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;


@WebServlet(name = "UpdateBookServlet", urlPatterns = "/books/update/*")

@MultipartConfig(location = "C:/Users/User/apps/library/upload")
public class UpdateBookServlet extends HttpServlet {

    private static final Path rootPath = Path.of(System.getProperty("user.home"), "/apps/library/upload");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/updatebook.jsp");
        response.setContentType("text/html");
        String pathInfo = request.getPathInfo();
        BookDAO bookDAO = BookDAO.getInstance();
        long studentID = Long.parseLong(pathInfo.substring(1));
        Book book = bookDAO.findById(studentID);
        request.setAttribute("book", book);
        CategoryDao categoryDao = CategoryDao.getInstance();
        List<Category> all = categoryDao.findAll();
        request.setAttribute("categories", all);
        dispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        BookDAO bookDAO = BookDAO.getInstance();
        int id=Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        String publisher = request.getParameter("publisher");
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String url = request.getParameter("url");
        int pages =Integer.parseInt(request.getParameter("pages"));
        Part file = request.getPart("file");
        Part image = request.getPart("image");
        Document image1 = saveCover(image);
        Document file1 = saveFile(file);

        LocalDate publishedat = LocalDate.parse(request.getParameter("publishedAt"));

        Book book = Book.builder()
                .id(id)
                .description(description)
                .publisher(publisher)
                .category(category_id)
                .title(title)
                .author(author)
                .url(url)
                .pages(pages)
                .coverId(image1.getId())
                .documentId(file1.getId())
                .publishedAt(publishedat)
                .build();

        bookDAO.update(book);
        response.sendRedirect("/booklist");
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
            throw new RuntimeException(e);
        }
    }
}
