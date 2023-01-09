package com.masai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

//import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class BolgAppApiApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(BolgAppApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(this.encoder.encode("xyz"));
		
	}


}


