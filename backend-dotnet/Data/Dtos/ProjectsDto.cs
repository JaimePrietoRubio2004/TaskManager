namespace backend_dotnet.Data.Dtos
{

    public class ProjectsDto
    {
        public ProjectsDto(long pk, string projectId, string name, string? description, DateOnly createdOn)
        {
            Pk = pk;
            ProjectId = projectId;
            Name = name;
            Description = description;
            CreatedOn = createdOn;
        }

        public ProjectsDto()
        {

        }

        public long? Pk { get; set; }
        public string? ProjectId { get; set; }
        public string? Name { get; set; }
        public string? Description { get; set; }
        public DateOnly? CreatedOn { get; set; }
    }
}
