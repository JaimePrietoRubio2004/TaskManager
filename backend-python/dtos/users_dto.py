from pydantic import BaseModel 
from datetime import date

class UsersDto(BaseModel):
  pk: int |None = None
  user_id: str | None = None
  name: str | None = None
  mail: str | None = None
  created_on: date | None = None

  @classmethod
  def from_dao(cls, u):
    return cls(
        pk=u.pk,
        user_id=u.user_id,
        name=u.name,
        mail = u.mail,
        created_on=u.created_on
        )
  @classmethod
  def from_dao_list(cls, u):
    return cls(
        pk=u.pk,
        user_id=u.user_id
        )
