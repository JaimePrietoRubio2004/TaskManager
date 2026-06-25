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

import TaskManager.backend_java.Dtos.UsersDto;
import TaskManager.backend_java.Services.UsersServices;

@RestController
@RequestMapping("/apiUser")
public class UserController {

	@Autowired
	private UsersServices usersServices;

	@PostMapping("/create")
	public ResponseEntity<Object> createUsers(@RequestBody UsersDto user) {
		try {
			return usersServices.createUsers(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@GetMapping("/allUsers")
	public ResponseEntity<Object> findAllUsers(@RequestBody UsersDto user) {
		try {
			return usersServices.findAllUsers(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Object> updateUser(@PathVariable String userId,
			@RequestBody UsersDto userDto) {
		try {
			if (userId == null || userDto == null || !userId.equals(userDto.getUserId())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The path ID does not match the body ID");
			}
			return usersServices.updateUser(userDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable String userId) {
		try {
			return usersServices.deleteUser(userId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There was a error. Please wait a few minutes while we resolve it.");
		}
	}
}
