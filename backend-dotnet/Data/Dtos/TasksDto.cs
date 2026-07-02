namespace backend_dotnet.Data.Dtos
{
    public class TasksDto
    {
        public TasksDto(long pk, string taskId, string title, string description, string state, string priority, DateOnly createdOn, DateOnly? limitDate, long projectFk, long? userFk)
        {
            Pk = pk;
            TaskId = taskId;
            Title = title;
            Description = description;
            State = state;
            Priority = priority;
            CreatedOn = createdOn;
            LimitDate = limitDate;
            ProjectFk = projectFk;
            UserFk = userFk;
        }

        public TasksDto()
        {

        }

        public long? Pk { get; set; }
        public string? TaskId { get; set; }
        public string? Title { get; set; }
        public string? Description { get; set; }
        public string? State { get; set; }
        public string? Priority { get; set; }
        public DateOnly? CreatedOn { get; set; }
        public DateOnly? LimitDate { get; set; }
        public long? ProjectFk { get; set; }
        public long? UserFk { get; set; }

    }
}
