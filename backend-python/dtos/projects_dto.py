from pydantic import BaseModel
from datetime import date

class ProjectDto(BaseModel):
  pk: int | None = None
  project_id: str | None = None
  name: str | None = None
  description: str | None = None
  created_on:  date | None = None

  @classmethod
  def from_dao(cls, p):
    return cls(
        pk=p.pk,
        project_id=p.project_id,
        name=p.name,
        description=p.description,
        created_on=p.created_on
        )
  @classmethod
  def from_dao_list(cls, p):
    return cls(
        pk=p.pk,
        project_id=p.project_id
        )
