package com.mingleHub.authsvc.exceptions;

import lombok.Data;
import org.springframework.web.client.HttpClientErrorException;

@Data
public class CustomHttpClientException extends RuntimeException{
  private final HttpClientErrorException cause;
  
  public CustomHttpClientException(HttpClientErrorException cause) {
	super(cause);
	this.cause = cause;
  }
  
  public HttpClientErrorException getCauseException() {
	return cause;
  }
}
