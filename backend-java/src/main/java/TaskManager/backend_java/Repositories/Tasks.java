package TaskManager.backend_java.Repositories;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks", schema = "taskmanager")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private Long pk;
    @Column(name = "task_id", length = 80)
    private String taskId = "taskId";
    @Column(name = "title", length = 150)
    private String title = "title";
    @Column(name = "description")
    private String description = "description";
    @Column(name = "state", length = 20)
    private String state = "state";
    @Column(name = "priority", length = 10)
    private String priority = "priority";
    @Column(name = "created_on")
    private LocalDate createdOn = LocalDate.now();
    @Column(name = "limit_date")
    private LocalDate limitDate = LocalDate.now();
    @Column(name = "project_fk", nullable = false)
    private Long projectFk;
    @Column(name = "user_fk", nullable = true)
    private Long userFk;

    public Tasks() {
    }

    public Tasks(String taskId, String title, String description, String state, String priority, LocalDate limitDate, LocalDate createdOn) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.state = state;
        this.priority = priority;
        this.limitDate = limitDate;
        this.createdOn = createdOn;
    }

    public Long getUserFk() {
        return userFk;
    }

    public void setUserFk(Long userFk) {
        this.userFk = userFk;
    }

    public Long getProjectFk() {
        return projectFk;
    }

    public void setProjectFk(Long proyectFk) {
        this.projectFk = proyectFk;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }
}
