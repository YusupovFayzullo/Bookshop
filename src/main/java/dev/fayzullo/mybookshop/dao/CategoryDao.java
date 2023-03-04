package dev.fayzullo.mybookshop.dao;

import dev.fayzullo.mybookshop.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends DAO<Category,Integer>{

    private static CategoryDao instance;

    public static final String FIND_ALL_QUERY = "select * from library.category";

    @Override
    public Category save(Category category) {

        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement("insert into  library.category(name) values(?)")) {
            ps.setString(1,category.getName());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public Category get(Integer integer) {
        return null;
    }

    @Override
    public boolean update(Category category) {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement("update library.category set name=? where id=?")) {
            ps.setString(1,category.getName());
            ps.setLong(2,category.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement("delete from library.category where id=?")) {
            ps.setLong(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public long totalCount() {
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement("select count(0) from library.category;")) {
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public  boolean findByName(String name) {
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement("select * from library.category where name=?")) {
            pr.setString(1, name);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Category findById(long categoryId) {
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement("select * from library.category t where t.id = ?;")) {
            pr.setLong(1, categoryId);
            ResultSet rs = pr.executeQuery();

            if (rs.next())
                return Category.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Category> findAll(short page, short size) {
        List<Category> categories = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement("select * from library.category offset ? limit ?")) {
            pr.setShort(1, (short) (page * size));
            pr.setShort(2, size);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                categories.add(Category.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement pr = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Category category=new Category(rs.getLong("id"),rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public static CategoryDao getInstance() {
        if (instance == null) {
            synchronized (CategoryDao.class) {
                if (instance == null) {
                    instance = new CategoryDao();
                }
            }
        }
        return instance;
    }
}
