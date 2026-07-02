using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

[Table("tasks", Schema = "taskmanager")]
public class Tasks
{
	public Tasks(){}	

	[Key]
	[Column("pk")]
	public long Pk {  get; set; }
	[Column("task_id")]
	public string TaskId {  get; set; }
	[Column("title")]
	public string Title {  get; set; }
    [Column("description")]
	public string Description {  get; set; }
    [Column("state")]
	public string State {  get; set; }
    [Column("priority")]
	public string Priority { get; set; }
    [Column("created_on")]
	public DateOnly CreatedOn { get; set; }
    [Column("limit_date")]
	public DateOnly? LimitDate { get; set; }
    [Column("project_fk")]
	public long ProjectFk {  get; set; }
    [Column("user_fk")]
	public long? UserFk { get; set; }


}
