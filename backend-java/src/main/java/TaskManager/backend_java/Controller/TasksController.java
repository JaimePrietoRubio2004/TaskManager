package TaskManager.backend_java.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import TaskManager.backend_java.Dtos.TasksDto;
import TaskManager.backend_java.Services.TasksService;

@RestController
@RequestMapping("/apiTasks")
public class TasksController {

	@Autowired
	private TasksService taskService;

	@PostMapping("/create")
	public ResponseEntity<Object> createTask(@RequestBody TasksDto taskDto) {
		try {
			return taskService.createTask(taskDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@GetMapping("/detailsTask")
	public ResponseEntity<Object> findTaskDetails(@RequestBody TasksDto taskDto) {
		try {
			return taskService.findTaskDetails(taskDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@GetMapping("/listTask")
	public ResponseEntity<Object> findTasksList(@RequestBody TasksDto taskDto) {
		try {
			return taskService.findTasksList(taskDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@PutMapping("{taskId}")
	public ResponseEntity<Object> updateTask(@PathVariable String taskId ,@RequestBody TasksDto taskDto) {
		if (taskId == null || taskDto == null|| !taskId.equals(taskDto.getTaskId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The path ID does not match the body ID");
		}
		try {
			return taskService.updateTask(taskDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@PutMapping("/modifyState")
	public ResponseEntity<Object> modifyState(@RequestParam(name = "taskId",required = true) String taskId, @RequestParam(name = "state",required = true) String state) {
		try {
			return taskService.modifyState(taskId, state);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@DeleteMapping("{taskId}")
	public ResponseEntity<Object> deleteTask(@PathVariable String taskId) {
		try {
			return taskService.deleteTask(taskId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

}
