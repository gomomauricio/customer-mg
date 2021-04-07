package net.paymentchain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerMgApplication {
	
	@Value("${spring.application.name}") //inyectamos el nombre de la aplicacion desde el aplication properties
	private String appName;
	

	public static void main(String[] args) {
		SpringApplication.run(CustomerMgApplication.class, args);
		System.out.println("\nCustomerMG  >>> iniciada . . . \n   " );
	}

}
