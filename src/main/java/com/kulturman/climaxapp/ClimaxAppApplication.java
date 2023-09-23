package com.kulturman.climaxapp;

import com.kulturman.climaxapp.domain.FileParserException;
import com.kulturman.climaxapp.domain.FileParserResolverInterface;
import com.kulturman.climaxapp.infra.ExtensionFileResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClimaxAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClimaxAppApplication.class, args);
	}

	@Bean
	FileParserResolverInterface fileParserResolverInterface() {
		return new ExtensionFileResolver();
	}
}
