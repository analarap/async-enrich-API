package com.desafiotres.compass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableFeignClients
@EnableJms
public class DesafioTresCompassApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTresCompassApplication.class, args);
	}

}
