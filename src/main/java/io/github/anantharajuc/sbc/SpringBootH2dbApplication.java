package io.github.anantharajuc.sbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching //enables Spring Caching functionality
public class SpringBootH2dbApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(SpringBootH2dbApplication.class, args);
	}
}
