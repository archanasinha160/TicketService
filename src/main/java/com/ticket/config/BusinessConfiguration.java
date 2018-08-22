package com.ticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ticket.service.TicketValidator;
import com.ticket.service.TicketValidatorImpl;


@Configuration
public class BusinessConfiguration {
    @Bean
    public TicketValidator ticketValidator(){
        return new TicketValidatorImpl();
    }
}
