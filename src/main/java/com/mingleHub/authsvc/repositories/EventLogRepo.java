package com.mingleHub.authsvc.repositories;

import com.mingleHub.authsvc.constants.EventType;
import com.mingleHub.authsvc.dao.EventLogDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EventLogRepo extends JpaRepository<EventLogDAO, Long> {
	Optional<EventLogDAO> findFirstByUserIdAndEventTypeOrderByIdDesc(String soulId, EventType eventType);
	Optional<EventLogDAO> findByUserIdAndReferenceId(String soulId, String referenceId);
}
