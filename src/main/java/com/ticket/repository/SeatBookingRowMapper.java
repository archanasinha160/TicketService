package com.ticket.repository;

import org.springframework.jdbc.core.RowMapper;

import com.ticket.common.entity.SeatBooking;
import com.ticket.repository.entity.TicketTableColumn;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * {@link RowMapper} that maps individual {@link ResultSet} to {@link SeatBooking}
 */
public class SeatBookingRowMapper implements RowMapper<SeatBooking> {
    @Override
    public SeatBooking mapRow(ResultSet rs, int rowNum) throws SQLException {
        SeatBooking seatBooking = new SeatBooking();
        seatBooking.setId(rs.getLong(TicketTableColumn.SEAT_BOOKING_ID.name()));
        seatBooking.setHoldId(rs.getLong(TicketTableColumn.HOLD_ID.name()));
        seatBooking.setNumberOfSeats(rs.getInt(TicketTableColumn.NUMBER_OF_SEATS.name()));
        seatBooking.setVenueId(rs.getInt(TicketTableColumn.LEVEL_ID.name()));
        return seatBooking;
    }
}
