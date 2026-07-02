using backend_dotnet.Data.Dtos;

namespace backend_dotnet.Services
{
    public class UsersServices
    {
        private readonly TaskManagerContext _dbContext;

        public UsersServices(TaskManagerContext ctx)
        {
            _dbContext = ctx;
        }

        public List<UsersDto> GetUsersList(UsersDto dto)
        {
            var query = _dbContext.Users.AsQueryable();
            if (!string.IsNullOrEmpty(dto.UserId)) { query.Where(u => u.UserId == dto.UserId); }
            if (!string.IsNullOrEmpty(dto.Name)) { query.Where(u => u.Name == dto.Name); }
            if (!string.IsNullOrEmpty(dto.Mail)) { query.Where(u => u.Mail == dto.Mail); }
            if (dto.CreatedOn.HasValue) { query.Where(u => u.CreatedOn == dto.CreatedOn); }
            return query.Select(u => new UsersDto(u.Pk, u.UserId, u.Name, u.Mail, u.CreatedOn)).ToList();
        }

        public UsersDto? GetUser(String userId)
        {
            if (userId == null) { throw new ArgumentNullException("userId"); }
            var user = _dbContext.Users.FirstOrDefault(u => u.UserId == userId);
            if (user == null) { return null; }
            return new UsersDto(user.Pk, user.UserId, user.Name, user.Mail, user.CreatedOn);
        }
        public bool CreateUser(UsersDto dto)
        {
            if (dto == null || dto.Mail == null || dto.UserId == null)
            {
                throw new ArgumentNullException();
            }
            var user = _dbContext.Users.FirstOrDefault(u => u.UserId == dto.UserId || u.Mail == dto.Mail);
            if (user != null) { return false; }
            user = new Users
            {
                UserId = dto.UserId,
                Name = dto.Name ?? "default",
                Mail = dto.Mail,
                CreatedOn = DateOnly.FromDateTime(DateTime.Now)
            };
            _dbContext.Users.Add(user);
            _dbContext.SaveChanges();
            return true;
        }

        public bool DeletedUsers(string userId)
        {
            if (userId == null)
            {
                throw new ArgumentNullException();
            }
            var user = _dbContext.Users.FirstOrDefault(u => u.UserId == userId);
            if (user == null) { return false; }
            _dbContext.Users.Remove(user);
            _dbContext.SaveChanges();
            return true;
        }

        public bool UpdateUser(string userId, UsersDto dto)
        {
            if (userId == null || dto == null) { throw new ArgumentNullException(); }
            var user = _dbContext.Users.FirstOrDefault(u => u.UserId == userId);
            if (user == null) { return false; }
            user.Name = dto.Name ?? user.Name;
            var isExistsUserMail = _dbContext.Users.FirstOrDefault(u => u.Mail == dto.Mail);
            if (isExistsUserMail != null && dto.Mail != user.Mail)
            {
                return false;
            }
            user.Mail = dto.Mail ?? user.Mail;
            _dbContext.Users.Update(user);
            _dbContext.SaveChanges();
            return true;
        }
    }
}
