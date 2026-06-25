package TaskManager.backend_java.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

	@Query("SELECT COUNT(t) > 0 FROM Tasks t WHERE t.taskId = :taskId")
	boolean existByTaskId(String taskId);

	Tasks findByTaskId(String taskId);

	@Query("SELECT t FROM Tasks t " + "WHERE (:state IS NULL OR LOWER(t.state) LIKE LOWER(CAST(:state AS String))) "
			+ "AND (:projectFk IS NULL OR t.projectFk = :projectFk) " + "AND (:userFk IS NULL OR t.userFk = :userFk)")
	List<Tasks> findByFilters(@Param("state") String state, @Param("projectFk") Long projectFk,
			@Param("userFk") Long userFk);

	@Query("SELECT t.taskId FROM Tasks t")
	List<String> findAllTaskIdList();

	Long countByProjectFk(Long projectFk);

}
