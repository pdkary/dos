package org.pdkary.dos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.pdkary.dos")
@SpringBootApplication
public class DosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DosApplication.class, args);
	}

}

