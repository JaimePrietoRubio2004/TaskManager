CREATE TABLE usuario (
id BIGSERIAL PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
email VARCHAR(150) NOT NULL UNIQUE,
fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE TABLE proyecto (
id BIGSERIAL PRIMARY KEY,
nombre VARCHAR(120) NOT NULL,
descripcion TEXT,
fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE TABLE tarea (
id BIGSERIAL PRIMARY KEY,
titulo VARCHAR(150) NOT NULL,
descripcion TEXT,
estado VARCHAR(20) NOT NULL DEFAULT 'TODO'
CHECK (estado IN ('TODO','IN_PROGRESS','DONE')),
prioridad VARCHAR(10) NOT NULL DEFAULT 'MEDIUM'
CHECK (prioridad IN ('LOW','MEDIUM','HIGH')),
fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT now(),
fecha_limite DATE,
proyecto_id BIGINT NOT NULL REFERENCES proyecto(id) ON DELETE CASCADE,
asignado_id BIGINT REFERENCES usuario(id) ON DELETE SET NULL
);
CREATE INDEX idx_tarea_proyecto ON tarea(proyecto_id);
CREATE INDEX idx_tarea_asignado ON tarea(asignado_id);
CREATE INDEX idx_tarea_estado ON tarea(estado);