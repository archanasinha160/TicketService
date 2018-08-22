package com.ticket.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ticket.common.entity.Customer;
import com.ticket.repository.entity.TicketTableColumn;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link ResultSetExtractor} that extract customer details from {@link ResultSet}
 */
public class CustomerExtractor implements ResultSetExtractor<Customer> {

    @Override
    public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
        Customer customer = null;
        if(rs.isBeforeFirst()){
            while(rs.next()){
                customer = new Customer();
                customer.setId(rs.getLong(TicketTableColumn.CUSTOMER_ID.name()));
                customer.setEmail(rs.getString(TicketTableColumn.EMAIL.name()));
            }
        }
        return customer;
    }
}
