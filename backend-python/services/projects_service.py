from sqlalchemy.orm import Session
from dtos.projects_dto import ProjectDto
from daos.projects import Projects
from datetime import date


def get_projects_list(dto : ProjectDto, db : Session):
  query = db.query(Projects)
  if dto:
    if dto.project_id:
      query = query.filter(Projects.project_id == dto.project_id)
    if dto.name:
      query = query.filter(Projects.name == dto.name)
    if dto.created_on:
      query = query.filter(Projects.created_on == dto.created_on)
  return [ProjectDto.from_dao_list(p) for p in query.all()]

def get_project(project_id: str, db: Session):
  if not project_id:
    return None
  project = db.query(Projects).filter(Projects.project_id == project_id).first()
  if not project:
    return None
  return ProjectDto.from_dao(project)

def create_project(dto: ProjectDto, db: Session):
  if not dto or not dto.project_id or not dto.name:
    return False
  exists = db.query(Projects).filter(Projects.project_id == dto.project_id).first()
  if exists:
    return False
  project = Projects(
    project_id=dto.project_id,
    name=dto.name,
    description=dto.description,
    created_on=date.today()
  )
  db.add(project)
  db.commit()
  return True

def delete_project(project_id : str, db : Session):
  if not project_id:
    return False
  project = db.query(Projects).filter(Projects.project_id == project_id).first()
  if not project:
    return False
  db.delete(project)
  db.commit()
  return True

def update_project(project_id : str, dto : ProjectDto, db : Session):
  if not project_id or not dto:
    return False
  project = db.query(Projects).filter(Projects.project_id == project_id).first()
  if not project:
    return False
  project.name = dto.name or project.name
  project.description = dto.description or project.description  
  db.commit()
  return True
