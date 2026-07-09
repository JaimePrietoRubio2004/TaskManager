from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from database import get_db
from dtos.tasks_dto import TaskDto
from services import tasks_service

controller = APIRouter(prefix="/apiTask", tags=["Tasks"])

@controller.post("/taskList")
def get_task_list(dto: TaskDto, db: Session = Depends(get_db)):
    try:
        result = tasks_service.get_task_list(dto, db)
        if not result:
            return "Don't exists tasks"
        return result
    except Exception as ex:
        return str(ex)

@controller.get("/{task_id}")
def get_task(task_id: str, db: Session = Depends(get_db)):
    try:
        result = tasks_service.get_task(task_id, db)
        if not result:
            return "Task doesn't exists"
        return result
    except Exception as ex:
        return str(ex)

@controller.post("/createTask")
def create_task(dto: TaskDto, db: Session = Depends(get_db)):
    try:
        result = tasks_service.create_task(dto, db)
        if result is True:
            return "Task created successfully"
        if result is False:
            return "Missing required fields"
        return result
    except Exception as ex:
        return str(ex)

@controller.put("/{task_id}")
def update_task(task_id: str, dto: TaskDto, db: Session = Depends(get_db)):
    try:
        result = tasks_service.update_task(task_id, dto, db)
        if result is True:
            return "Task updated successfully"
        if result is False:
            return "Task doesn't exists"
        return result
    except Exception as ex:
        return str(ex)

@controller.put("/updateState/{task_id}")
def update_state(task_id: str, state: str, db: Session = Depends(get_db)):
    try:
        result = tasks_service.update_state(task_id, state, db)
        if result is True:
            return "State updated successfully"
        if result is False:
            return "Task doesn't exists or missing data"
        return result
    except Exception as ex:
        return str(ex)

@controller.delete("/{task_id}")
def delete_task(task_id: str, db: Session = Depends(get_db)):
    try:
        result = tasks_service.delete_task(task_id, db)
        if result is True:
            return "Task deleted successfully"
        return "Task doesn't exists"
    except Exception as ex:
        return str(ex)
