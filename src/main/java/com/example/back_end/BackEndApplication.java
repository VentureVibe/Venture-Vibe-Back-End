package com.example.back_end;

import com.example.back_end.dto.ExperienceDTO;
import com.example.back_end.dto.ServiceProviderDTO;
import com.example.back_end.model.Experience;
import com.example.back_end.model.TravelGuide;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;

@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
		/*return new ModelMapper();*/
	}
}
