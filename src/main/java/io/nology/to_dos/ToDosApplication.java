package io.nology.to_dos;
// import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDosApplication {

	public static void main(String[] args) {
		// Dotenv dotenv = Dotenv.load();
		SpringApplication.run(ToDosApplication.class, args);
	}

}
