package com.hackathon.findtogether;

import com.hackathon.findtogether.util.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({FileUploadProperties.class})
@SpringBootApplication
public class FindtogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindtogetherApplication.class, args);
	}

}
