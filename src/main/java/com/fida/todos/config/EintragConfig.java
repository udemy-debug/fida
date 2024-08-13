package com.fida.todos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EintragConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // ignore null attributes on the source object on copying
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        return modelMapper;
    }
}
