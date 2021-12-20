package com.bridgelabz.lmscandidate.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * purpose to create object in bean
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 *
 */
@Configuration
public class HireCandidateConfiguration {


	/**
	 * purpose to  map DTOs into entities
	 * 
	 */
	@Bean
	public ModelMapper modelMapper() 
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

}
