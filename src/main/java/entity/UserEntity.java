package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user", schema = "public", catalog = "s243884")
@Entity
public class UserEntity {
    @Id
    @Column(name = "login", nullable = false, length = 50)
    private String login;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
}
