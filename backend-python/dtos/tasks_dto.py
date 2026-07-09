from pydantic import BaseModel 
from datetime import date

class TaskDto(BaseModel):
  pk: int | None = None
  task_id: str | None = None
  title: str | None = None
  description: str | None = None
  state: str | None = None
  priority: str | None = None
  created_on: date | None = None
  limit_date: date | None = None
  project_fk: int | None = None
  project_id : str | None = None
  user_fk: int | None = None
  user_id : str | None = None

  @classmethod
  def from_dao(cls, t, db):
    from daos.users import Users
    from daos.projects import Projects
    user = db.query(Users).filter(Users.pk == t.user_fk).first()
    project = db.query(Projects).filter(Projects.pk == t.project_fk).first()
    return cls(
        pk=t.pk,
        task_id=t.task_id,
        title=t.title,
        description = t.description,
        state = t.state,
        priority = t.priority,
        created_on=t.created_on,
        limit_date=t.limit_date,
        user_id=user.user_id if user else None,
        project_id=project.project_id if project else None
        )
  @classmethod
  def from_dao_list(cls, t):
    return cls(
        pk=t.pk,
        title=t.title,
        state=t.state,
        priority=t.priority,
        created_on=t.created_on,
        limit_date=t.limit_date
        )