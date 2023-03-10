package dev.fayzullo.mybookshop.bookservlets.document;

import dev.fayzullo.mybookshop.dao.DocumentDAO;
import dev.fayzullo.mybookshop.model.Document;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@WebServlet(name = "UploadsServlet", value = "/upload")
@MultipartConfig(location = "D:/files")
public class UploadsServlet extends HttpServlet {

    private static final Path rootPath = Path.of("D:/files");

    @Override
    public void init() throws ServletException {
        if (!Files.exists(rootPath)) {
            try {
                Files.createDirectories(rootPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/upload/upload.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part file = request.getPart("file");
//        AsyncSupplier.run(() -> {
            try {
                DocumentDAO documentDAO = DocumentDAO.getInstance();
                String originalName = file.getSubmittedFileName();
                String extension = originalName.substring(originalName.lastIndexOf("."));
                String generatedName = UUID.randomUUID() + extension;
                String mimeType = file.getContentType();
                System.out.println(mimeType);
                long size = file.getSize();
                Document document = Document.builder()
                        .generatedFileName(generatedName)
                        .originalFileName(originalName)
                        .fileSize(size)
                        .mimeType(mimeType)
                        .filePath(rootPath.resolve(generatedName).toString())
                        .extension(extension)
                        .build();
                documentDAO.save(document);
                file.write(generatedName);
            } catch (Exception e) {
                // TODO: 08/02/23 redirect to error page
                throw new RuntimeException(e);
            }
//        });
        response.sendRedirect("/main.jsp");
    }
}
