package travelmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;
@SpringBootApplication
@EnableFeignClients
@CrossOrigin(origins = "http://localhost:4200")
public class TravelMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelMasterApplication.class, args);
	}

}
	