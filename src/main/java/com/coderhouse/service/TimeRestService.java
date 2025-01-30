package com.coderhouse.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.dtos.TimeRestDto;

@Service
public class TimeRestService {
	@Autowired
	private RestTemplate restTem;
	
	public LocalDateTime obtenerFechaActual() {
		try {
			final String URL = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
			TimeRestDto response = restTem.getForObject(URL, TimeRestDto.class);
			return LocalDateTime.parse(response.getDateTime());
			
			
		}catch(RestClientException e) {
			System.err.println("Error al conectar con la API externa: " + e.getMessage());
	        return LocalDateTime.now();
		}
		
	}
}
	
