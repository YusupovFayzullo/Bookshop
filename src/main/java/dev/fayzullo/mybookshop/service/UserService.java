package dev.fayzullo.mybookshop.service;

import dev.fayzullo.mybookshop.model.User;
import org.postgresql.Driver;

import java.sql.*;

public class UserService {

    public static String message(User user,String pre){

        if(pre==null || pre.isBlank()){
            return "Parol tasdiqini  kiriting!";
        }
        if(!user.getEmail().contains("@")){
            return "Emailda @ bo'lishi kerak!";
        }
        if(user.getFirstName()==null || user.getFirstName().isBlank()){
            return "Ism bo'sh bo'lishi mumkin emas!";
        }
        if(user.getLastName()==null || user.getLastName().isBlank()){
            return "Familiya bo'sh bo'lishi mumkin emas!";
        }
        if(user.getEmail()==null || user.getEmail().isBlank() || !user.getEmail().contains("@")){
            return "Email bo'sh bo'lishi mumkin emas!";
        }
        if(user.getPassword()==null || user.getPassword().isBlank()){
            return "Password bo'sh bo'lishi mumkin emas!";
        }

        return "ok";
    }


}
