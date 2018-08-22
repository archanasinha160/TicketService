package com.ticket.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ticket.common.entity.Venue;
import com.ticket.repository.entity.TicketTableColumn;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @link ResultSetExtractor} that extracts venue details from {@link ResultSet}
 */
public class VenueExtractor implements ResultSetExtractor<Venue> {
    @Override
    public Venue extractData(ResultSet rs) throws SQLException, DataAccessException {
        Venue venue = null;
        if(rs.isBeforeFirst()){
            while(rs.next()){
                venue = new Venue();
                venue.setLevelId(rs.getInt(TicketTableColumn.LEVEL_ID.name()));
                venue.setLevelName(rs.getString(TicketTableColumn.LEVEL_NAME.name()));
                venue.setPrice(rs.getBigDecimal(TicketTableColumn.PRICE.name()));
                venue.setRows(rs.getInt(TicketTableColumn.NUMBER_OF_ROWS.name()));
                venue.setSeatsInRow(rs.getInt(TicketTableColumn.SEATS_IN_ROW.name()));
            }
        }
        return venue;
    }
}
