package dev.fayzullo.mybookshop.dao;


import dev.fayzullo.mybookshop.model.Category;
import dev.fayzullo.mybookshop.model.User;
import lombok.NonNull;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DAO<User, Integer> {

    private static final String INSER_QUERY = """
            insert into library.users(first_name, last_name, email, password)
            values (?,?,?,?); """;

    private static final String SELECT_ALL = """
            select * from library.users;
            """;
    private static final String UPDATE_QUERY = """
            update library.users set first_name=?,last_name=?,email=?,password=?, is_deleted=?
            where id=?;
            """;

    private static UserDao instance;

    public boolean checkEmail(String email) {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement("select * from library.users where email=?")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User save(User user) {

        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSER_QUERY)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, decodePassword(user.getPassword()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    public List<User> findAll() {
        Connection connection = getConnection();
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("email"),rs.getBoolean("is_deleted"),rs.getString("role"), rs.getBoolean("is_blocked"));
                if(!user.getRole().equalsIgnoreCase("admin"))
                    users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    @Override
    public User get(Integer integer) {
        return null;
    }


    @Override
    public boolean update(User user) {


        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, decodePassword(user.getPassword()));
            ps.setBoolean(5, user.isDeleted());
            ps.setLong(6, user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    public String decodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(@NonNull String password, @NonNull String codePassword) {
        return BCrypt.checkpw(password, codePassword);
    }

    public static UserDao getInstance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    public String loginCheckUser(String email, String password) {

        boolean a = false, b = false;
        String realPassword = "";
        String checkPasword = "select password from library.users where email=?";
        String checkEmail = "select email from library.users where email=?";
        String role = "";

        Connection connection = getConnection();

        try (PreparedStatement ps = connection.prepareStatement("select role from library.users where email=?")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                role = rs.getString("role");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = connection.prepareStatement(checkEmail)) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                a = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!a) {
            return "Email not found";
        }

        try (PreparedStatement ps = connection.prepareStatement(checkPasword)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                b = true;
                realPassword = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!b) {
            return "Password not found";
        }
        if (checkPassword(password, realPassword)) {
            if (a && b && role.equalsIgnoreCase("admin")) {
                return "admin";
            } else if (a && b && role.equalsIgnoreCase("user"))
                return "ok";
        }
        return "Password incorrect";
    }

}
