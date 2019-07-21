package de.allianz.swdemo.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CryptIdGenerator {
	
	public String generateCryptId() {
		String cryptId = UUID.randomUUID().toString();
		return cryptId.replace("-", "");
	}

}
