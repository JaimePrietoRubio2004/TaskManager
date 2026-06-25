package TaskManager.backend_java.Repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", schema = "taskmanager")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private Long pk;
    @Column(name = "user_id", length = 80)
    private String userId = "userId";
    @Column(name = "name", length = 100)
    private String name = "name";
    @Column(name = "mail", length = 150)
    private String mail = "mail";
    @Column(name = "created_on")
    private LocalDate createdOn = LocalDate.now();

    public Users() {
    }

    public Users(String userId, String name, String mail, LocalDate createdOn) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.createdOn = createdOn;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
}
