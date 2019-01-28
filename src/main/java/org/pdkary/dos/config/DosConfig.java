package org.pdkary.dos.config;

import org.pdkary.dos.utils.ExcelWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DosConfig {
	
	@Bean
	public ExcelWriter csvWriter() {
		return new ExcelWriter();
	}
}
