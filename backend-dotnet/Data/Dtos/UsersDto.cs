

namespace backend_dotnet.Data.Dtos
{
    public class UsersDto
    {
        public UsersDto(long pk, string userId, string name, string mail, DateOnly createdOn)
        {
            Pk = pk;
            UserId = userId;
            Name = name;
            Mail = mail;
            CreatedOn = createdOn;
        }

        public UsersDto()
        {

        }

        public long? Pk { get; set; }
        public string? UserId { get; set; } = string.Empty;
        public string? Name { get; set; } = string.Empty;
        public string? Mail { get; set; } = string.Empty;
        public DateOnly? CreatedOn { get; set; }

    }
}
