package com.store.fini.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    ModelMapper modelMapper() {
    	ModelMapper mapper = new ModelMapper();
    	mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    	mapper.getConfiguration().setAmbiguityIgnored(true);

    	return mapper;
    }
}
