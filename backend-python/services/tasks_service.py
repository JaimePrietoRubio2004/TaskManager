from sqlalchemy.orm import Session
from dtos.tasks_dto import TaskDto
from daos.tasks import Tasks
from daos.users import Users
from daos.projects import Projects
from datetime import date

STATE_VARIABLES = ["TODO", "IN_PROGRESS", "DONE"]
PRIORITY_VARIABLES = ["Low", "High"]

def get_task_list(dto: TaskDto, db : Session):
  query = db.query(Tasks)
  if dto.task_id:
    query = query.filter(Tasks.task_id == dto.task_id)
  if dto.title:
    query = query.filter(Tasks.title == dto.title)
  if dto.state:
    query = query.filter(Tasks.state == dto.state)
  if dto.priority:
    query = query.filter(Tasks.priority == dto.priority)
  if dto.created_on:
    query = query.filter(Tasks.created_on == dto.created_on)
  if dto.limit_date:
    query = query.filter(Tasks.limit_date == dto.limit_date)
  if dto.user_id:
    user = db.query(Users).filter(Users.user_id == dto.user_id).first()
    if user:
      query = query.filter(Tasks.user_fk == user.pk)
  if dto.project_id:
    project = db.query(Projects).filter(Projects.project_id == dto.project_id).first()
    if project:
      query = query.filter(Tasks.project_fk == project.pk)
  return [TaskDto.from_dao_list(t) for t in query.all()]


def get_task(task_id : str, db : Session):
  if not task_id:
    return None
  task = db.query(Tasks).filter(Tasks.task_id == task_id).first()
  if not task:
    return None
  return TaskDto.from_dao(task,db)

def create_task(dto : TaskDto , db :Session):
  if not dto or not dto.title or (not dto.state and dto.state not in STATE_VARIABLES) or (not dto.priority and dto.priority not in PRIORITY_VARIABLES) or not dto.project_id:
    return False
  if dto.user_id:
    user = db.query(Users).filter(Users.user_id == dto.user_id).first()
    if not user:
      return "User not found"
    dto.user_fk = user.pk
  project = db.query(Projects).filter(Projects.project_id == dto.project_id).first()
  if not project:
    return "Project not found"
  dto.project_fk = project.pk
  task = Tasks(
        task_id = auto_task_id(project.project_id, project.pk, db),
        title=dto.title,
        description = dto.description,
        state = dto.state,
        priority = dto.priority,
        created_on=date.today(),
        limit_date=dto.limit_date,
        user_fk=dto.user_fk,
        project_fk=dto.project_fk
        )  
  db.add(task)
  db.commit()
  return True

def auto_task_id(project_id: str, project_pk: int, db: Session):
  count = db.query(Tasks).filter(Tasks.project_fk == project_pk).count()
  return project_id + "-" + str(count + 1).zfill(4)
  

def update_task(task_id : str, dto : TaskDto, db : Session):
  if not task_id:
    return False
  task = db.query(Tasks).filter(Tasks.task_id == task_id).first()
  if not task:
    return False
  if dto.state and dto.state not in STATE_VARIABLES:
    return "State value is not valid"
  if dto.priority and dto.priority not in PRIORITY_VARIABLES:
    return "Priority value is not valid"
  task.title = dto.title or task.title
  task.description = dto.description or task.description
  task.state = dto.state or task.state
  task.priority = dto.priority or task.priority
  task.limit_date = dto.limit_date or task.limit_date
  if dto.user_id:
    user = db.query(Users).filter(Users.user_id == dto.user_id).first()
    if not user:
      return "User not found"
    task.user_fk = user.pk
  db.commit()
  return True

def update_state(task_id : str, state : str, db : Session):
  if not task_id or not state:
    return False
  task = db.query(Tasks).filter(Tasks.task_id == task_id).first()
  task.state = state
  db.commit()
  return True

def delete_task(task_id : str, db : Session):
  if not task_id:
    return False
  task = db.query(Tasks).filter(Tasks.task_id == task_id).first()
  if not task: 
    return False
  db.delete(task)
  db.commit()
  return True