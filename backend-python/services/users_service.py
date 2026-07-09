from sqlalchemy.orm import Session
from sqlalchemy import or_
from dtos.users_dto import UsersDto
from daos.users import Users
from datetime import date

def get_user_list(dto : UsersDto, db : Session):
  query = db.query(Users)
  if dto.user_id:
    query = query.filter(Users.user_id == dto.user_id)
  if dto.name: 
    query = query.filter(Users.name == dto.name)
  if dto.created_on:
    query = query.filter(Users.created_on == dto.created_on)  
  return [UsersDto.from_dao_list(u) for u in query.all()]
  
def get_user(user_id : str, db : Session):
  if not user_id:
    return None
  result = db.query(Users).filter(Users.user_id == user_id).first()
  if not result:
    return None
  return UsersDto.from_dao(result)

def create_user(dto : UsersDto, db : Session):
  if not dto or not dto.user_id or not dto.mail:
    return False
  result = db.query(Users).filter(or_(Users.user_id == dto.user_id , Users.mail == dto.mail)).first()
  if result:
    return False
  user = Users(
    user_id = dto.user_id,
    name = dto.name,
    mail = dto.mail,
    created_on = date.today()
  )
  db.add(user)
  db.commit()
  return True

def update_user(user_id : str, dto : UsersDto, db : Session):
  if not user_id :
    return False
  user = db.query(Users).filter(Users.user_id == user_id).first()
  if not user:
    return False
  user.name = dto.name or user.name
  if dto.mail and user.mail != dto.mail:
    mail_exists = db.query(Users).filter(Users.mail == dto.mail).first()
    if mail_exists:
      return "Mail not update because Mail exists in other user"
    user.mail = dto.mail
  db.commit()
  return True

def delete_user(user_id : str, db : Session):
  if not user_id:
    return False
  user = db.query(Users).filter(Users.user_id == user_id).first()
  if not user:
    return False
  db.delete(user)
  db.commit()
  return True
