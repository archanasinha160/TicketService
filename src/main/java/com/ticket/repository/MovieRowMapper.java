package com.ticket.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ticket.common.entity.Venue;
import com.ticket.controller.entity.MovieData;
import com.ticket.repository.entity.TicketTableColumn;

public class MovieRowMapper implements RowMapper<MovieData>{
	  @Override
	    public MovieData mapRow(ResultSet rs, int rowNum) throws SQLException {
	        MovieData md = new MovieData();
	        md.setMovieId(rs.getInt(TicketTableColumn.MOVIE_ID.name()));
	        md.setMovieName(rs.getString(TicketTableColumn.MOVIE_NAME.name()));
	        md.setMovieActor(rs.getString(TicketTableColumn.MOVIE_ACTOR.name()));
	        md.setMovieStar(rs.getString(TicketTableColumn.MOVIE_STAR.name()));
	       
	        return md;
	    }

}
