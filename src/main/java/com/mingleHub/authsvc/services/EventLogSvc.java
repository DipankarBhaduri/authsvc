package com.mingleHub.authsvc.services;


import com.mingleHub.authsvc.dto.event.EventLog;

public interface EventLogSvc {
  void record(EventLog eventLog, String userID);
}
