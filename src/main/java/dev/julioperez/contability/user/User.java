package dev.julioperez.contability.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "userid")
    private Long userId;

    private String username;

    private String password;

    private String email;

    private Instant created;

    private boolean enable;

    private Long idRol;

    public User(){}

    public User(Long userId, String username, String password, String email, Instant created, boolean enable, Long idRol) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
        this.enable = enable;
        this.idRol = idRol;
    }

    public User(String username, String password, String email, Instant created, boolean enable, Long idRol) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
        this.enable = enable;
        this.idRol = idRol;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
