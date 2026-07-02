package TaskManager.backend_java.Controller;

import java.io.InvalidObjectException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import TaskManager.backend_java.Dtos.ProjectsDto;
import TaskManager.backend_java.Services.ProjectService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/apiProject")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping("/create")
	public ResponseEntity<String> createProyect(@RequestBody ProjectsDto project) {
		try {
			projectService.createProject(project);
		} catch (InvalidObjectException | MatchException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
		return ResponseEntity.status(HttpStatus.OK).body("The project will be created");
	}

	@GetMapping("/allProject")
	public ResponseEntity<Object> findAllProject(@RequestParam(name = "projectId", required = false) String projectId,
			@RequestParam(name = "createdOn", required = false) LocalDate createdOn) {
		try {
			List<ProjectsDto> projectsList = new ArrayList<>();
			projectsList = projectService.findAllProjects(projectId, createdOn);
			if (projectsList.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body("The project list is empty");
			}
			return ResponseEntity.status(HttpStatus.OK).body(projectsList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<Object> findListProject(@PathVariable String projectId) {
		try {
			if (projectId == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The path ID does not match the body ID");
			}
			return projectService.findProjectId(projectId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@PutMapping("/{projectId}")
	public ResponseEntity<Object> modifyProject(@PathVariable String projectId,
			@Valid @RequestBody ProjectsDto projectDto) {
		try {
			if (projectId == null || projectDto == null || !projectId.equals(projectDto.getProjectId())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The path ID does not match the body ID");
			}
			return projectService.updatePoject(projectDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@DeleteMapping("{projectId}")
	public ResponseEntity<Object> deleteProject(@PathVariable String projectId) {
		try {
			return projectService.deleteProject(projectId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}
}
