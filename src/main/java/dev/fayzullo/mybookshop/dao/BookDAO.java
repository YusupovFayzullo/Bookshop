package dev.fayzullo.mybookshop.dao;


import dev.fayzullo.mybookshop.dto.BookDetailsDTO;
import dev.fayzullo.mybookshop.model.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDAO extends DAO<Book, Integer> {

    private static final String SELECT_ALL = """
            select b.id, b.title, b.author,b.description, b.url, b.views,b.likes, b.dislikes, b.downloads,b.pages,b.publishedat, b.publisher,
             d.originalFileName as coverOriginalFileName ,d.generatedFileName as coverGeneratedFileName,d.fileSize as coverFileSize, 
             d2.originalFileName as documentOriginalFileName ,d2.generatedFileName as documentGeneratedFileName,d2.fileSize as documentFileSize,
              c.name as category 
              from library.book b 
             inner join library.document d on b.coverid = d.id 
             inner join library.document d2 on b.document_id = d2.id 
             inner join library.category c on b.category_id = c.id where b.is_deleted=false;
                        """;
    public static final String INSERT_QUERY = """
            insert into library.book (
                        title,
                        author,
                        category_id,
                        pages,
                        publisher,
                        publishedat,
                        coverid,
                        document_id,
                        description
            ) values (?, ?, ?, ?, ?, ?, ?, ?,?) returning id;""";
    private static final String UPDATE_BOOK = """
            update library.book set title = ?, author = ?, category_id = ?, pages = ?, publisher = ?, publishedat = ?, coverid = ?, document_id = ?, description = ? where id = ?;
                        """;

    private static final String DELETE_BOOK = """
            update library.book  set is_deleted=true where id = ?;
                        """;

    private static BookDAO instance;


    @Override
    public Book save(Book book) {
        Connection connection = getConnection();
        try (var pr = connection.prepareStatement(INSERT_QUERY)) {
            pr.setString(1, book.getTitle());
            pr.setString(2, book.getAuthor());
            pr.setInt(3, book.getCategory());
            pr.setInt(4, book.getPages());
            pr.setString(5, book.getPublisher());
            pr.setDate(6, Date.valueOf(book.getPublishedAt()));
            pr.setInt(7, book.getCoverId());
            pr.setInt(8, book.getDocumentId());
            pr.setString(9, book.getDescription());
            ResultSet rs = pr.executeQuery();
            if (rs.next())
                book.setId(rs.getInt("id"));
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book get(Integer integer) {
        return null;
    }

    @Override
    public boolean update(Book book) {

        Connection connection = getConnection();
        try (var pr = connection.prepareStatement(UPDATE_BOOK)) {
            pr.setString(1, book.getTitle());
            pr.setString(2, book.getAuthor());
            pr.setInt(3, book.getCategory());
            pr.setInt(4, book.getPages());
            pr.setString(5, book.getPublisher());
            pr.setDate(6, Date.valueOf(book.getPublishedAt()));
            pr.setInt(7, book.getCoverId());
            pr.setInt(8, book.getDocumentId());
            pr.setString(9, book.getDescription());
            pr.setInt(10, book.getId());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(Integer integer) {

        Connection connection = getConnection();
        try (var pr = connection.prepareStatement(DELETE_BOOK)) {
            pr.setInt(1, integer);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<BookDetailsDTO>  findAll() {
        int c = 0;
        Connection connection = getConnection();
        List<BookDetailsDTO> books = new ArrayList<>();
        try (var pr = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                books.add(BookDetailsDTO.builder()
                        .count(++c)
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .author(resultSet.getString("author"))
                        .description(resultSet.getString("description"))
                        .pages(resultSet.getInt("pages"))
                        .publishedAt(resultSet.getDate("publishedat").toLocalDate())
                        .likes(resultSet.getInt("likes"))
                        .dislikes(resultSet.getInt("dislikes"))
                        .views(resultSet.getInt("views"))
                        .category(resultSet.getString("category"))
                        .url(resultSet.getString("url"))
                        .publisher(resultSet.getString("publisher"))
                        .coverOriginalFileName(resultSet.getString("coverOriginalFileName"))
                        .coverGeneratedFileName(resultSet.getString("coverGeneratedFileName"))
                        .coverFileSize((resultSet.getLong("coverFileSize") / 2 << 20) + "MB")
                        .documentOriginalFileName(resultSet.getString("documentOriginalFileName"))
                        .documentGeneratedFileName(resultSet.getString("documentGeneratedFileName"))
                        .documentFileSize((resultSet.getLong("documentFileSize") / 2 << 20) + "MB")
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static BookDAO getInstance() {
        if (instance == null) {
            synchronized (BookDAO.class) {
                if (instance == null) {
                    instance = new BookDAO();
                }
            }
        }
        return instance;
    }
    public Book findById(long bookId) {
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement("select * from library.book t where t.is_deleted=false and t.id = ?;")) {
            pr.setLong(1, bookId);
            ResultSet rs = pr.executeQuery();

            if (rs.next())
                return Book.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("title"))
                        .author(rs.getString("author"))
                        .coverId(rs.getInt("coverid"))
                        .documentId(rs.getInt("document_id"))
                        .category(rs.getInt("category_id"))
                        .pages(rs.getInt("pages"))
                        .publisher(rs.getString("publisher"))
                        .publishedAt(rs.getDate("publishedat").toLocalDate())
                        .description(rs.getString("description"))
                        .url(rs.getString("url"))
                        .build();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public List<Book> getByName(String name, Integer category_id){
//
//        Connection connection = getConnection();
//        String substring = name.substring(0, 1);
//
//        List<Book> books=new ArrayList<>();
//        if(category_id==0){
//            try(PreparedStatement ps= connection.prepareStatement("select * from library.book where title ilike '"+substring+"%' ") ){
//                ResultSet rs =ps.executeQuery();
//                while (rs.next())
//                 books.add(Book.builder()
//                         .id(rs.getInt("id"))
//                         .title(rs.getString("title"))
//                         .author(rs.getString("author"))
//                         .coverId(rs.getInt("coverid"))
//                         .documentId(rs.getInt("document_id"))
//                         .category(rs.getInt("category_id"))
//                         .pages(rs.getInt("pages"))
//                         .publisher(rs.getString("publisher"))
//                         .publishedAt(rs.getDate("publishedat").toLocalDate())
//                         .description(rs.getString("description"))
//                         .url(rs.getString("url"))
//                         .build());
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            return books;
//        }
//        try(PreparedStatement ps= connection.prepareStatement("select * from library.book where title ilike '"+substring+"%' and category_id=?") ){
//            ps.setInt(2,category_id);
//            ResultSet rs =ps.executeQuery();
//            if(rs.next())
//            return Book.builder()
//                    .id(rs.getInt("id"))
//                    .title(rs.getString("title"))
//                    .author(rs.getString("author"))
//                    .coverId(rs.getInt("coverid"))
//                    .documentId(rs.getInt("document_id"))
//                    .category(rs.getInt("category_id"))
//                    .pages(rs.getInt("pages"))
//                    .publisher(rs.getString("publisher"))
//                    .publishedAt(rs.getDate("publishedat").toLocalDate())
//                    .description(rs.getString("description"))
//                    .url(rs.getString("url"))
//                    .build();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
}
