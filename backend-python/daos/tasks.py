from sqlalchemy import Column, BigInteger, String, Date 
from database import Base

class Tasks(Base):
    __tablename__ = "tasks"
    
    pk = Column(BigInteger, primary_key = True, autoincrement = True)
    task_id = Column(String(80), nullable = False)
    title = Column(String(120), nullable = False)
    description = Column(String, nullable = True)
    state = Column(String(20), nullable = False)
    priority = Column(String(20), nullable = False)
    created_on = Column(Date, nullable = False)
    limit_date = Column(Date, nullable = True)
    project_fk = Column(BigInteger, nullable = False)
    user_fk = Column(BigInteger, nullable = True)