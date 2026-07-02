using backend_dotnet.Data.Dtos;
using backend_dotnet.Services;
using Microsoft.AspNetCore.Mvc;

namespace backend_dotnet.Controllers
{
    [Route("apiProjects")]
    [ApiController]
    public class ProjectsController : ControllerBase
    {
        private readonly ProjectsServices _service;

        public ProjectsController(ProjectsServices service)
        {
            _service = service;
        }

        [HttpGet("projectList")]
        public IActionResult ProjectsList([FromQuery] string? projectId, [FromQuery] DateOnly? createOn)
        {
            try
            {
                List<ProjectsDto> dtoList = _service.GetProjectList(projectId, createOn);
                if (!dtoList.Any()) return Ok("Don't exists projects");
                return Ok(dtoList);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpGet("{projectId}")]
        public IActionResult GetProject(string projectId)
        {
            try
            {
                ProjectsDto? dto = _service.GetProject(projectId);
                if (dto == null)
                {
                    return Ok("Project " + projectId + " is not exists");
                }
                return Ok(dto);

            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpPost("createProject")]
        public IActionResult CreateProject([FromBody] ProjectsDto projectDto)
        {
            try
            {
                bool isExist = _service.CreateProjects(projectDto);
                if (isExist)
                {
                    return Ok("Create Projects Successfully");
                }
                return BadRequest("Create Projects not Successfully");
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpPut("{projectId}")]
        public IActionResult UpdateProject(string projectId, [FromBody] ProjectsDto projectDto)
        {
            try
            {
                bool isExists = _service.UpdateProject(projectId, projectDto.Name, projectDto.Description);
                if (isExists)
                {
                    return Ok("Update Projects Successfully");
                }
                return BadRequest("Update Projects not Successfully");
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpDelete("{projectId}")]
        public IActionResult DeleteProject(String projectId)
        {
            try
            {
                bool isExists = _service.DeletedProject(projectId);
                if (isExists)
                {
                    return Ok("Delete Projects Successfully");
                }
                return BadRequest("Delete Projects not Successfully");
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }
    }
}

