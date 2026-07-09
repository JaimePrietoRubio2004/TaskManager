from sqlalchemy import Column, BigInteger, String, Date  
from database import Base

class Users(Base):
    __tablename__ = "users"
    
    pk = Column(BigInteger, primary_key = True, autoincrement = True)
    user_id = Column(String(80), nullable = False)
    name = Column(String(120), nullable = False)
    mail = Column(String(120), nullable = False)
    created_on = Column(Date, nullable = False)