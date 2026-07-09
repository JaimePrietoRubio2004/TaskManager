from sqlalchemy import create_engine, MetaData 
from sqlalchemy.orm import sessionmaker, declarative_base

DATABASE_URL = "postgresql://postgres:12345@localhost:5432/postgres"

engine = create_engine(
    DATABASE_URL,
    connect_args={"options": "-csearch_path=taskmanager"}
)

SessionLocal = sessionmaker(bind=engine)

metadata = MetaData(schema="taskmanager")
Base = declarative_base(metadata=metadata)

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()