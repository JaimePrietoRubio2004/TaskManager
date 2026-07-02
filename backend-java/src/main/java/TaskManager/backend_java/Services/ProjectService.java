package TaskManager.backend_java.Services;

import java.io.InvalidObjectException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import TaskManager.backend_java.Dtos.ProjectsDto;
import TaskManager.backend_java.Repositories.Projects;
import TaskManager.backend_java.Repositories.ProjectsRepository;
import jakarta.transaction.Transactional;

@Service
public class ProjectService {
	@Autowired
	public ProjectsRepository projectRepository;

	public void createProject(ProjectsDto project) throws InvalidObjectException, MatchException {
		if (project == null) {
			throw new InvalidObjectException("This Project is empty");
		}
		Projects projectsList = projectRepository.findByProjectId(project.getProjectId());
		Boolean isExist = projectsList != null ? true : false;
		if (isExist) {
			throw new MatchException("This Projecty " + project.getProjectId() + " is exist", null);
		}
		Projects projectEntity = new Projects();
		projectEntity.setprojectId(project.getProjectId());
		projectEntity.setName(project.getName());
		projectEntity.setDescription(project.getDescription());
		projectEntity.setCreatedOn(LocalDate.now());
		projectRepository.save(projectEntity);
	}

	public List<ProjectsDto> findAllProjects(String projectId, LocalDate createdOn) {
		List<Projects> projectsList = projectRepository.findWithFiltersProjects(projectId, createdOn);
		List<ProjectsDto> allProjects = new ArrayList<>();
		if (projectsList.isEmpty()) {
			return allProjects;
		}
		for (Projects project : projectsList) {
			ProjectsDto projectDto = new ProjectsDto();
			projectDto.setPk(project.getPk());
			projectDto.setProjectId(project.getProjectId());
			projectDto.setName(project.getName());
			projectDto.setDescription(project.getDescription());
			projectDto.setCreatedOn(project.getCreatedOn());
			allProjects.add(projectDto);
		}
		return allProjects;
	}

	public ResponseEntity<Object> updatePoject(ProjectsDto projectDto) {
		Projects project = projectRepository.findByProjectId(projectDto.getProjectId());
		if (project == null) {
			return ResponseEntity.status(HttpStatus.OK).body("The project does not exits");
		}
		if (projectDto.getName() != null) {
			project.setName(projectDto.getName());
		}
		if (projectDto.getDescription() != null) {
			project.setDescription(projectDto.getDescription());
		}
		if (projectDto.getCreatedOn() != null) {
			project.setCreatedOn(projectDto.getCreatedOn());
		}
		projectRepository.save(project);
		return ResponseEntity.status(HttpStatus.OK)
				.body(String.format("Modified %s successfully", projectDto.getProjectId()));
	}

	public ResponseEntity<Object> findProjectId(String projectId) {
		Projects project = projectRepository.findByProjectId(projectId);
		if (project == null) {
			return ResponseEntity.status(HttpStatus.OK).body("The project does not exits");
		}
		ProjectsDto dto = new ProjectsDto(project.getProjectId(), project.getName(), project.getDescription(),
				project.getCreatedOn());
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@Transactional
	public ResponseEntity<Object> deleteProject(String projectId) {
		if (projectId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The path ID does not exits");
		}
		projectRepository.deleteByProjectId(projectId);
		Projects project = projectRepository.findByProjectId(projectId);
		if (project != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete doesn't successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Deleted %s successfully", projectId));
	}
}
