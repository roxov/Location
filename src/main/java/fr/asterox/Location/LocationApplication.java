package fr.asterox.Location;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.asterox")
public class LocationApplication {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SpringApplication.run(LocationApplication.class, args);
	}

}
