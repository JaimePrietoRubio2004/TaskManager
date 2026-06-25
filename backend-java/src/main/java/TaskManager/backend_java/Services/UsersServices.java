package TaskManager.backend_java.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import TaskManager.backend_java.Dtos.UsersDto;
import TaskManager.backend_java.Repositories.Users;
import TaskManager.backend_java.Repositories.UsersRepository;
import jakarta.transaction.Transactional;

@Service
public class UsersServices {

	@Autowired
	private UsersRepository userRepository;

	public ResponseEntity<Object> createUsers(UsersDto userDto) {
		if (userDto == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User is null");
		}
		Users user = userRepository.findByUserId(userDto.getUserId());
		user = user == null ? userRepository.findByMail(userDto.getMail()) : user;
		if (user != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User is exits");
		}
		user = new Users();
		user.setUserId(userDto.getUserId());
		user.setMail(userDto.getMail());
		user.setName(userDto.getName());
		user.setCreatedOn(LocalDate.now());
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.OK).body("Created user successfully");
	}

	public ResponseEntity<Object> findAllUsers(UsersDto userDto) {
		if (userDto == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User is null");
		}
		List<UsersDto> usersDtoList = new ArrayList<>();
		List<Users> usersList = userRepository.findByFilters(userDto.getMail(), userDto.getName(), userDto.getUserId(),
				userDto.getCreatedOn());
		if (usersList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Users List is Empty");
		}
		for (Users user : usersList) {
			UsersDto usersDto = new UsersDto(user.getPk(), user.getUserId(), user.getName(), user.getMail(),
					user.getCreatedOn());
			usersDtoList.add(usersDto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(usersDtoList);
	}

	public ResponseEntity<Object> updateUser(UsersDto userDto) {
		Users user = userRepository.findByUserId(userDto.getUserId());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not exits");
		}
		if (userDto.getName() != null) {
			user.setName(userDto.getName());
		}
		if (userDto.getCreatedOn() != null) {
			user.setCreatedOn(userDto.getCreatedOn());
		}
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body(String.format("Modified %s successfully", userDto.getUserId()));
	}

	@Transactional
	public ResponseEntity<Object> deleteUser(String userId) {
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Parameter is null");
		}
		userRepository.deleteByUserId(userId);
		Users users = userRepository.findByUserId(userId);
		return users == null ? ResponseEntity.status(HttpStatus.OK).body("Deleted is successly")
				: ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Deleted isn't successly");
	}
}
