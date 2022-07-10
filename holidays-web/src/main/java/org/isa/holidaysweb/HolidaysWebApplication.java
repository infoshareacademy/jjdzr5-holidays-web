package org.isa.holidaysweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HolidaysWebApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(HolidaysWebApplication.class);
	public static void main(String[] args) {
		LOGGER.info("*Starting HolidaysWebApplication*");
		SpringApplication.run(HolidaysWebApplication.class, args);
	}
}
