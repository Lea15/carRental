package com.efrei.JPAExample;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaExampleApplication {
	
	private static final Logger log = LoggerFactory.getLogger(JpaExampleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaExampleApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(CarRepository repository) {
		return (args) -> {
			
			/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse("2018-10-09");
			log.info(date.toString());*/

			Person lea = new Person(1, "Lea", 20);
			Car ferrari = new Car(1, "1122", "ferrari", 5, 100, true, 10);
			lea.getCar().add(ferrari);
			ferrari.setPerson(lea);
			repository.save(lea);
		};
	}

}
