package com.ticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ticket.repository.TicketRepository;
import com.ticket.service.TicketService;
import com.ticket.service.TicketServiceImpl;
import com.ticket.service.TicketValidator;


@Configuration
public class ServiceConfiguration {

    @Bean
    public TicketService ticketService(final TicketValidator ticketValidator,
                                       final TicketRepository ticketRepository,
                                       final Environment environment){
        return new TicketServiceImpl(ticketValidator, ticketRepository,
                environment.getProperty("seat.hold.expiration.seconds", Integer.class));
    }

}
