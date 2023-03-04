package dev.fayzullo.mybookshop.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isDeleted;
    private String role;
    private boolean isBlocked;


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String firstName, String email, boolean isDeleted) {
        this.firstName = firstName;
        this.email = email;
        this.isDeleted = isDeleted;
    }

    public User(Long id, String firstName, String email, boolean isDeleted, String role, boolean isBlocked) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.isDeleted = isDeleted;
        this.role = role;
        this.isBlocked = isBlocked;
    }
}
