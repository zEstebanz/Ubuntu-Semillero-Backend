package com.semillero.ubuntu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

//Colocar la variable de entorno en el lugar de la URL
@CrossOrigin(origins = "http://localhost:5173")
@SpringBootApplication
public class UbuntuApplication {

	public static void main(String[] args) {

		SpringApplication.run(UbuntuApplication.class, args);
	}

}
