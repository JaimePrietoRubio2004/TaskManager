from fastapi import APIRouter, Depends, Body
from sqlalchemy.orm import Session
from database import get_db
from dtos.users_dto import UsersDto 
from services import users_service

controller = APIRouter(prefix="/apiUsers", tags=["Users"])

@controller.get("/userList")
def get_user_list(dto : UsersDto, db : Session = Depends(get_db)):
  try:
    result = users_service.get_user_list(dto, db)
    if not result:
      return "Users list is empty"
    return result
  except Exception as ex:
    return str(ex)

@controller.get("/{user_id}")
def get_user(user_id : str, db : Session = Depends(get_db)):
  try:
    result = users_service.get_user(user_id, db)
    if not result:
      return "User not exists"
    return result
  except Exception as ex:
    return str(ex)

@controller.post("/createUser")
def create_user(dto : UsersDto, db : Session = Depends(get_db)):
  try:
    result = users_service.create_user(dto, db)
    if result is False:
      return "User already exists or Id or mail is empty"
    return "Create User Successfully"
  except Exception as ex:
    return str(ex)

@controller.put("/{user_id}")
def update_user(user_id : str, dto : UsersDto, db : Session = Depends(get_db)):
  try:
    result = users_service.update_user(user_id, dto, db)
    if result is False:
      return "User not exists"
    if isinstance(result, str):
      return result
    return "Update User Successfully"
  except Exception as ex:
    return str(ex)

@controller.delete("/{user_id}")
def delete_user(user_id : str, db : Session = Depends(get_db)):
  try:
    result = users_service.delete_user(user_id, db)
    if result is False:
      return "User no exists"
    return "Delete User Successfully"
  except Exception as ex:
    return str(ex)