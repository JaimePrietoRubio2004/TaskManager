package TaskManager.backend_java.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
	Projects findByProjectId(String projectId);

	@Query(value = "Select p from Projects p where (:projectId IS NULL OR LOWER(p.projectId) LIKE LOWER(CONCAT(CAST(:projectId AS String), '%'))) AND (CAST(:createdOn AS date) IS NULL OR p.createdOn = :createdOn)")
	List<Projects> findWithFiltersProjects(@Param("projectId") String projectId,
			@Param("createdOn") LocalDate createdOn);
	
	 void deleteByProjectId(String projectId);

		@Query(value = "SELECT p.projectId FROM Projects p where p.pk = :pk ")
		String findProjectIdByPk(@Param("pk") Long pk);
		
		@Query(value = "SELECT p.pk FROM Projects p where p.projectId = :projectId ")
		Long findPkByProjectId(@Param("projectId") String projectId);;
}
