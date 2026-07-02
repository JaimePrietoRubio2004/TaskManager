using Microsoft.EntityFrameworkCore;

    public class TaskManagerContext : DbContext
{
    public TaskManagerContext(DbContextOptions<TaskManagerContext> options) : base(options){}

    protected TaskManagerContext()
    {
    }

    public DbSet<Tasks> Tasks { get; set;}
    public DbSet<Users> Users { get; set;}
    public DbSet<Projects> Projects { get; set;}

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.HasDefaultSchema("taskmanager");
    }

}