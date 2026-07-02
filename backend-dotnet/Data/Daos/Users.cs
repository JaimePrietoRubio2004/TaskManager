using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

[Table("users", Schema = "taskmanager")]
public class Users
{
    public Users()
    {
        
    }

    [Key]
    [Column("pk")]
    public long Pk { get; set; }

    [Column("user_id")]
    public string UserId { get; set; } = string.Empty;

    [Column("name")]
    public string Name { get; set; } = string.Empty;

    [Column("mail")]
    public string Mail { get; set; } = string.Empty;

    [Column("created_on")]
    public DateOnly CreatedOn { get; set; }
}