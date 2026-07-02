namespace backend_dotnet.Data.Dtos
{

    public class TaskListDto
    {

        public TaskListDto()
        {

        }

        public TaskListDto(string? taskId, string? title, string? description, string? state, string? priority, DateOnly? createdOn, DateOnly? limitDate, string? projectId, long? projectFk, string? userId, long? userFk)
        {
            TaskId = taskId;
            Title = title;
            Description = description;
            State = state;
            Priority = priority;
            CreatedOn = createdOn;
            LimitDate = limitDate;
            ProjectId = projectId;
            ProjectFk = projectFk;
            UserId = userId;
            UserFk = userFk;
        }

        public string? TaskId { get; set; }
        public string? Title { get; set; }
        public string? Description { get; set; }
        public string? State { get; set; }
        public string? Priority { get; set; }
        public DateOnly? CreatedOn { get; set; }
        public DateOnly? LimitDate { get; set; }
        public long? ProjectFk { get; set; }
        public string? ProjectId { get; set; }
        public long? UserFk { get; set; }
        public string? UserId { get; set; }
    }
}