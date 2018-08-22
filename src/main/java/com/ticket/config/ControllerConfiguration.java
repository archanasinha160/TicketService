package com.ticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ticket.controller.TicketController;
import com.ticket.exception.handler.TicketExceptionHandler;
import com.ticket.service.TicketService;


@Configuration
public class ControllerConfiguration {
    @Bean
    public TicketController ticketController(final TicketService ticketService){
        return new TicketController(ticketService);
    }

    @Bean
    public TicketExceptionHandler ticketExceptionHandler(){
        return new TicketExceptionHandler();
    }
}
