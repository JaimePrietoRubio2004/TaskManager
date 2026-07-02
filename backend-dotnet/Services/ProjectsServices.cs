using backend_dotnet.Data.Dtos;

namespace backend_dotnet.Services
{
    public class ProjectsServices
    {
        private readonly TaskManagerContext _dbContext;

        public ProjectsServices(TaskManagerContext dbContext)
        {
            _dbContext = dbContext;
        }

        public List<ProjectsDto> GetProjectList(string? projectId, DateOnly? createdOn)
        {
            var query = _dbContext.Projects.AsQueryable();
            if (!string.IsNullOrEmpty(projectId))
            {
                query = query.Where(p => p.ProjectId == projectId);
            }
            if (createdOn.HasValue)
            {
                query = query.Where(p => p.CreatedOn == createdOn.Value);
            }
            return query
                .Select(p => new ProjectsDto(p.Pk, p.ProjectId, p.Name, p.Description, p.CreatedOn))
                .ToList();
        }

        public ProjectsDto? GetProject(string projectId)
        {
            if (string.IsNullOrEmpty(projectId))
            {
                return null;
            }
            return _dbContext.Projects.Where(p => p.ProjectId == projectId).Select(p => new ProjectsDto(p.Pk, p.ProjectId, p.Name, p.Description, p.CreatedOn)).FirstOrDefault();
        }

        public bool CreateProjects(ProjectsDto dto)
        {
            if (dto == null || dto.ProjectId == null || dto.Name == null)
            {
                return false;
            }
            bool isExist = _dbContext.Projects.Any(p => p.ProjectId == dto.ProjectId);
            if (isExist) { return false; }
            Projects project = new Projects();
            project.ProjectId = dto.ProjectId;
            project.Name = dto.Name;
            project.Description = dto.Description;
            project.CreatedOn = DateOnly.FromDateTime(DateTime.Now);
            _dbContext.Projects.Add(project);
            _dbContext.SaveChanges();
            isExist = _dbContext.Projects.Any(p => p.ProjectId == dto.ProjectId);
            if (isExist) { return true; } else { return false; }
        }

        public bool UpdateProject(string projectId, string? name, string? description)
        {
            if (projectId == null) { return false; }
            var project = _dbContext.Projects.FirstOrDefault(p => p.ProjectId == projectId);
            if (project == null) { return false; }
            project.Name = name ?? project.Name;
            project.Description = description ?? project.Description;
            _dbContext.SaveChanges();
            return true;
        }

        public bool DeletedProject(string projectId)
        {
            if (projectId == null) { return false; }
           var project = _dbContext.Projects.FirstOrDefault(p => p.ProjectId == projectId);
            if(project == null) { return false; }
            _dbContext.Projects.Remove(project);
            _dbContext.SaveChanges();
            return true;
        }
    }
}
