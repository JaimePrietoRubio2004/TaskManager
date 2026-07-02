using backend_dotnet.Data.Dtos;
using backend_dotnet.Services;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Mvc;

namespace backend_dotnet.Controllers
{
    [Route("apiUsers")]
    [ApiController]
    public class UsersController : ControllerBase
    {
        private readonly UsersServices _usersService;

        public UsersController(UsersServices usersService)
        {
            _usersService = usersService;
        }

        [HttpGet("usersList")]
        public IActionResult UseresList([FromBody] UsersDto dto)
        {
            try
            {
                List<UsersDto> userList = _usersService.GetUsersList(dto);
                if (!userList.Any())
                {
                    return Conflict("Any users exists");
                }
                return Ok(userList);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpGet("{userId}")]
        public IActionResult Get(string userId)
        {
            try
            {
                UsersDto? dto = _usersService.GetUser(userId);
                if (dto == null)
                {
                    return Conflict("Users doesn't exists");
                }
                return Ok(dto);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpPost("createUsers")]
        public IActionResult CreateUsers([FromBody] UsersDto dto)
        {
            try
            {
                bool isCreated = _usersService.CreateUser(dto);
                if (isCreated) { return Ok("User created successfully"); } else { return Conflict("User ID or Mail is exists"); }
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }

        }

        [HttpPut("{userId}")]
        public IActionResult UpdateUser(string userId, [FromBody] UsersDto dto)
        {
            try
            {
                bool isUpdate = _usersService.UpdateUser(userId, dto);
                if (isUpdate) { return Ok("User Update successfully"); } else { return Conflict("Usernot exists or Mail is exists"); }
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpDelete("{userId}")]
        public IActionResult Delete(string userId)
        {
            try
            {
                bool isDeleted = _usersService.DeletedUsers(userId);
                if (isDeleted) { return Ok("Deleted is Successfully"); } else { return Conflict("Deleted is Successfully"); }
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }
    }
}

