package TaskManager.backend_java.Dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class UsersDto {

    private Long pk;
    private String userId = "userId";
    private String name = "name";
    private String mail = "mail";
    private LocalDate createdOn = LocalDate.now();

    public UsersDto() {
    }

    public UsersDto(Long pk,String userId, String name, String mail, LocalDate createdOn) {
    	this.pk = pk;
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
