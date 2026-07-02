using backend_dotnet.Data.Dtos;
using backend_dotnet.Services;
using Microsoft.AspNetCore.Mvc;

namespace backend_dotnet.Controllers
{
    [Route("apiTask")]
    [ApiController]
    public class TasksController : ControllerBase
    {
        private TasksServices taskServices;

        public TasksController(TasksServices taskServices)
        {
            this.taskServices = taskServices;
        }

        [HttpGet("taskList")]
        public IActionResult GetTaskList([FromBody] TaskListDto filter)
        {
            try
            {
                List<TaskListDto> dtoList = taskServices.GetTasksList(filter);
                if (!dtoList.Any()) return Ok("Don't exists tasks");
                return Ok(dtoList);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpGet("{taskId}")]
        public IActionResult GetTask(string taskId)
        {
            try
            {
                TaskListDto? dto = taskServices.GetTask(taskId);
                if (dto == null) return Ok("Task " + taskId + " is not exists");
                return Ok(dto);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpPost("createtask")]
        public IActionResult CreateTask([FromBody] TasksDto dto)
        {
            try
            {
                bool isCreated = taskServices.CreateTask(dto);
                if (isCreated) return Ok("Create Task Successfully");
                return BadRequest("Create Task not Successfully");
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpPut("{taskId}")]
        public IActionResult UpdateTask(string taskId, [FromBody] TasksDto dto)
        {
            try
            {
                bool isUpdated = taskServices.UpdateTask(taskId, dto);
                if (isUpdated) return Ok("Update Task Successfully");
                return BadRequest("Update Task not Successfully");
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpPut("updateState/{taskId}")]
        public IActionResult UpdateState(string taskId, [FromBody] string state)
        {
            try
            {
                bool isUpdated = taskServices.UpdateState(taskId, state);
                if (isUpdated) return Ok("Update State Successfully");
                return BadRequest("Update State not Successfully");
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpDelete("{taskId}")]
        public IActionResult DeleteTask(string taskId)
        {
            try
            {
                bool isDeleted = taskServices.DeleteTask(taskId);
                if (isDeleted) return Ok("Delete Task Successfully");
                return BadRequest("Delete Task not Successfully");
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }
    }
}
