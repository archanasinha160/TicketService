package com.ticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ticket.repository.TicketRepository;
import com.ticket.repository.TicketRepositoryImpl;

import javax.sql.DataSource;


@Configuration
public class RepositoryConfiguration {

    @Bean
    public TicketRepository ticketRepository(final DataSource dataSource,
                                             final Environment environment){
        final TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl(environment.getProperty("query.find.venue.all")
                , environment.getProperty("query.find.venue.by.level")
                , environment.getProperty("query.find.seat.hold.by.id")
                , environment.getProperty("query.find.customer.by.email")
                , environment.getProperty("query.find.customer.by.id")
                , environment.getProperty("query.find.seat.booking.by.level")
                , environment.getProperty("query.find.seat.hold.expired")
                , environment.getProperty("query.find.seat.booking.by.hold.id")
                , environment.getProperty("query.save.customer")
                , environment.getProperty("query.save.seat.hold")
                , environment.getProperty("query.save.seat.booking")
                , environment.getProperty("query.delete.seat.hold")
                , environment.getProperty("query.delete.seat.booking")
                , environment.getProperty("query.update.seat.hold.update.by.id")
                ,environment.getProperty("query.get.All.movie.list"));
        ticketRepository.setDataSource(dataSource);
        return ticketRepository;
    }
}
