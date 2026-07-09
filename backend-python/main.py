from fastapi import FastAPI
from database import engine, Base
from controllers import projects_controller, users_controller, tasks_controller

Base.metadata.create_all(bind=engine)

app = FastAPI(
    title="TaskManager API",
    description="Backend Python — FastAPI + SQLAlchemy",
    version="1.0.0"
)

app.include_router(projects_controller.controller)
app.include_router(users_controller.controller)
app.include_router(tasks_controller.controller)
