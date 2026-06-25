package TaskManager.backend_java.Repositories;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="projects", schema = "taskmanager")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private long pk = 0;
    @Column(name = "project_id" , length = 80)
    private String projectId = "projectId";
    @Column(name = "name", length = 120)
    private String name = "name";
    @Column(name = "description")
    private String description = "description";
    @Column(name = "created_on")
    private LocalDate createdOn;

    public Projects(String projectId, String name, String description, LocalDate createdOn) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.createdOn = createdOn;
    }

    public Projects() {
    }

    public long getPk() {
        return pk;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setprojectId(String projectId) {
        this.projectId = projectId;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
}