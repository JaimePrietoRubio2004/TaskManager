package TaskManager.backend_java.Dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ProjectsDto {
   
    private long pk = 0;
	private String projectId = "projectId";
    private String name = "name";
    private String description = "description";
    private LocalDate createdOn;

    public ProjectsDto(String projectId, String name, String description, LocalDate createdOn) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.createdOn = createdOn;
    }

    public ProjectsDto() {
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

    public void setProjectId(String projectId) {
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