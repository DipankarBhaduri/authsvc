package com.mingleHub.authsvc.utils;

import com.mingleHub.authsvc.exceptions.RestTemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class RestTemplateHelper {
	private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateHelper(
            RestTemplateBuilder restTemplateBuilder
    ) {
		int CONNECTION_TIMEOUT = 50000;
		int READ_TIMEOUT = 50000;
		this.restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.of(CONNECTION_TIMEOUT, ChronoUnit.MILLIS))
                .setReadTimeout(Duration.of(READ_TIMEOUT, ChronoUnit.MILLIS))
                .build();
    }
	
    private HttpHeaders getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    public <T, R> R postForObject(String uri, String path, T requestBody, Class<R> responseType) {
        try {
		    UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
															  .fromUriString(uri)
															  .path(path);
		  
		    HttpEntity<?> httpEntity = new HttpEntity<>(requestBody, getHttpHeader());
		    return restTemplate.postForObject(uriComponentsBuilder.toUriString(), httpEntity, responseType);
		} catch (HttpClientErrorException e) {
			throw new RestTemplateException("ERROR :: Error during postForObject API call", e);
        } catch (Exception e) {
            log.error("ERROR :: error during postForObject :: {}", e.getMessage(), e);
            throw new RestTemplateException("ERROR :: error during postForObject API call", e);
        }
    }
	
	public <T> T getFromExternalServer(String uri, String path, Class<T> responseType) {
		UriComponentsBuilder uriComponentsBuilder =
				UriComponentsBuilder
						.fromUriString(uri)
						.path(path);
		
		HttpEntity<?> request = new HttpEntity<>(null, getHttpHeader());
		return restTemplate.exchange(
				uriComponentsBuilder.toUriString(),
				HttpMethod.GET,
				request,
				responseType
		).getBody();
	}
}