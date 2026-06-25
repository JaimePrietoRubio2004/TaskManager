package TaskManager.backend_java.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Users findByUserId(String userId);

	Users findByMail(String mail);

	@Query(value = "SELECT u FROM Users u"
			+ "    WHERE (:mail IS NULL OR LOWER(u.mail) LIKE LOWER(CONCAT(CAST(:mail AS String), '%')))"
			+ "      AND (:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT(CAST(:name AS String), '%')))"
			+ "      AND (:userId IS NULL OR LOWER(u.userId) LIKE LOWER(CONCAT(CAST(:userId AS String), '%')))"
			+ "      AND (CAST(:createdOn AS date) IS NULL OR u.createdOn = :createdOn)")
	List<Users> findByFilters(@Param("mail") String mail, @Param("name") String name, @Param("userId") String userId,
			@Param("createdOn") LocalDate createdOn);

	void deleteByUserId(String userId);

	@Query(value = "SELECT u.userId FROM Users u where u.pk = :pk ")
	String findUserIdByPk(@Param("pk") Long pk);
	
	@Query(value = "SELECT u.pk FROM Users u where u.userId = :userId ")
	Long findPkByUserId(@Param("userId") String userId);
}
