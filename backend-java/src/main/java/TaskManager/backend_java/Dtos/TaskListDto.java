package TaskManager.backend_java.Dtos;

import java.time.LocalDate;

public class TaskListDto {

	private String taskId = "TaskId";
	private String asigned = "asigned";
	private String projectId = "projectId";
	private String state = "state";
	private String priority = "Low";
	private LocalDate createdOn = LocalDate.now();

	public TaskListDto() {
		super();
	}

	public TaskListDto(String taskId, String asigned, String projectId, String state, String priority,
			LocalDate createdOn) {
		super();
		this.taskId = taskId;
		this.asigned = asigned;
		this.projectId = projectId;
		this.state = state;
		this.priority = priority;
		this.createdOn = createdOn;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAsigned() {
		return asigned;
	}

	public void setAsigned(String asigned) {
		this.asigned = asigned;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

}
