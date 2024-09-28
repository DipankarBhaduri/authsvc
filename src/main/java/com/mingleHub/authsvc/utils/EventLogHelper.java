package com.mingleHub.authsvc.utils;

import com.mingleHub.authsvc.constants.EventType;
import com.mingleHub.authsvc.dto.event.EventLog;
import com.mingleHub.authsvc.services.EventLogSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class EventLogHelper {
	private final EventLogSvc eventLogSvc;
	@Autowired
	public EventLogHelper (
			EventLogSvc eventLogSvc
	) {
		this.eventLogSvc = eventLogSvc;
	}
	
	public void createEventLog(
			EventType eventType, String userId,
			Map<String, String> data, String referenceId
	) {
		EventLog eventLog =
				new EventLog()
						.setReferenceId(referenceId)
						.setEventType(eventType)
						.setData(data)
						.setTime(LocalDateTime.now());
		
		eventLogSvc.record(eventLog, userId);
	}
}
