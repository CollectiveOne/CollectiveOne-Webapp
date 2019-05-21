package org.collectiveone.modules.uprcl.repositories;

import java.util.List;

import org.collectiveone.modules.uprcl.entities.Perspective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PerspectiveRepositoryIf extends JpaRepository<Perspective, String> {
	
	@Query("SELECT persp.id FROM Perspective persp "
			+ "WHERE persp.contextId = :contextId")
	public String findIdOfOldestOfContext(
			@Param("contextId") String contextId);
	
	public List<Perspective> findByContextId(String contextId);
}
