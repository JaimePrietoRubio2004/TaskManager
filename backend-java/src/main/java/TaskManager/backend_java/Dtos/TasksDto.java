package TaskManager.backend_java.Dtos;

import java.time.LocalDate;

public class TasksDto {
	private Long pk;
	private String taskId = "taskId";
	private String title = "title";
	private String description = "description";
	private String state = "state";
	private String priority = "priority";
	private LocalDate createdOn = LocalDate.now();
	private LocalDate limitDate = LocalDate.now();
	private Long projectFk;
	private String projectId;
	private Long userFk;
	private String userId;

	public TasksDto() {
	}

	public TasksDto(Long pk, String taskId, String title, String description, String state, String priority,
			LocalDate limitDate, LocalDate createdOn, Long projectFk, Long userFk, String projectId, String userId) {
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.state = state;
		this.priority = priority;
		this.limitDate = limitDate;
		this.createdOn = createdOn;
		this.projectFk = projectFk;
		this.userFk = userFk;
		this.projectId = projectId;
		this.userId = userId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public void setProjectFk(Long projectFk) {
		this.projectFk = projectFk;
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
