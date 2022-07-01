package com.charlie.addressbook.config;

import com.charlie.addressbook.repository.ContactRepository;
import com.charlie.addressbook.repository.ContactRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
class RepositoryConfiguration {
    @Bean
    ContactRepository contactRepository(JdbcTemplate jdbcTemplate){
        return new ContactRepositoryImpl(jdbcTemplate);
    }

}
