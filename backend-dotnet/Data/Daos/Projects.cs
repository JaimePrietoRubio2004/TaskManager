using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

[Table("projects", Schema = "taskmanager")]
public class Projects
{
	public Projects()
	{
	}

	[Key]
	[Column("pk")]
	public long Pk {  get; set; }
	[Column("project_id")]
	public string ProjectId {  get; set; }
	[Column("name")]
	public string Name {  get; set; }
	[Column("description")]
	public string? Description { get; set; }
	[Column("created_on")]
	public DateOnly CreatedOn { get; set; }
}
