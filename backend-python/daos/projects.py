from sqlalchemy import Column, String, BigInteger, Date 
from database import Base

class Projects(Base):
    __tablename__ = "projects"
    # Only if you want to override schema
    # __table_args__ = {"schema": "taskmanager"}

    pk = Column(BigInteger, primary_key= True, autoincrement = True)
    project_id = Column(String(80), nullable= False)
    name = Column(String(120), nullable = False)
    description = Column(String, nullable = True)
    created_on = Column(Date, nullable = False)