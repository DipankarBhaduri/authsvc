package com.mingleHub.authsvc.dto.event;

import com.mingleHub.authsvc.constants.EventType;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Accessors(chain = true)
public class EventLog {
  private String referenceId;
  private EventType eventType;
  private Map<String, String> data;
  private LocalDateTime time;
}