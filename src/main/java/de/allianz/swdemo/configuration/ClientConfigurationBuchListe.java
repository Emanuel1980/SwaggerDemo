package de.allianz.swdemo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfigurationBuchListe {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClientConfigurationBuchListe.class);
	
	@Bean
	public RestTemplate buchListeRestTemplate() {
		LOG.debug("Create BuchListe RestTemplate");
		
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
		
	}

	

}
