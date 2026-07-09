from fastapi import APIRouter, Depends, Body
from sqlalchemy.orm import Session
from database import get_db
from dtos.projects_dto import ProjectDto
from services import projects_service

controller = APIRouter(prefix="/apiProject", tags=["Projects"])

@controller.get("/projectList")
def get_project_list(dto: ProjectDto = Body(default={}), db: Session = Depends(get_db)):
  try:
    result = projects_service.get_projects_list(dto,db)
    if not result:
      return "Don't exists projects"
    return result
  except Exception as ex:
    return str(ex)


@controller.get("/{project_id}")
def get_project(project_id : str, db : Session = Depends(get_db)):
  try:
    result = projects_service.get_project(project_id, db)
    if not result:
      return "Don't exist project " + project_id 
    return result
  except Exception as ex:
    return str(ex)

@controller.post("/createProject")
def create_project(dto : ProjectDto, db : Session = Depends(get_db)):
  try:
    result = projects_service.create_project(dto,db)
    if result is False:
      return "Project already exists or Id or name is empty"
    return "Create Project Seccessfully"
  except Exception as ex:
    return str(ex)

@controller.put("/{project_id}")
def update_project(project_id : str, dto : ProjectDto, db: Session = Depends(get_db)):
  try:
    result = projects_service.update_project(project_id, dto, db)
    if result is False:
      return "Project not exists"
    return "Update Projects Successfully"
  except Exception as ex:
    return str(ex)

@controller.delete("/{project_id}")
def delete_project(project_id : str, db : Session = Depends(get_db)):
  try:
    result = projects_service.delete_project(project_id, db)
    if result is False:
      return "Project not exists"
    return "Delete Project Successfully"
  except Exception as ex:
    return str(ex)