package com.ticket.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticket.common.entity.SeatHold;
import com.ticket.controller.entity.*;
import com.ticket.service.TicketService;
import com.ticket.util.TicketUtils;

import java.util.List;


@RestController
public class TicketController {
    private static final Logger LOGGER = LogManager.getLogger(TicketController.class);

    private final TicketService ticketService;

    public TicketController(final TicketService ticketService) {
        this.ticketService = ticketService;
    }

    
    /**
    *
    * @param moviedata
    * @return boolean Response 
    */

   @RequestMapping(value = "/addMovies", method = RequestMethod.POST)
   public boolean addMovies(@RequestBody final MovieData movieData){
       LOGGER.debug("Received add movies request with input {}", movieData);
      boolean response = ticketService.addMovies(movieData);   
       LOGGER.debug("Response for addMovies with input request {} is {}", movieData, response);
       return response;
   }
   
   /**
   *
   * 
   * @return all movies as list 
   */

  @RequestMapping(value = "/showMovies", method = RequestMethod.POST)
  public List<MovieData> showAllMovies(){
      LOGGER.debug("Received showAllMovie request with input {}");
      List movielist=ticketService.showAllMovies();
      return movielist;
      
  }

   
    /**
     *
     * @param seatHoldRequest Request contains numSeats, customerEmail, minLevel, maxLevel
     * @return SeatHoldReply Response contains id, customerEmail, list of Holds at various levels
     */

    @RequestMapping(value = "/holdSeats", method = RequestMethod.POST)
    public ResponseEntity<SeatHoldReply> findAndHoldSeats(@RequestBody final SeatHoldRequest seatHoldRequest){
        LOGGER.debug("Received seat hold request with input {}", seatHoldRequest);
        final SeatHold seatHold = ticketService.findAndHoldSeats(
                seatHoldRequest.getNumSeats(), seatHoldRequest.getMinLevel(),
                seatHoldRequest.getMaxLevel(), seatHoldRequest.getCustomerEmail());

        final ResponseEntity<SeatHoldReply> response;

        if(seatHold != null){
            response = new ResponseEntity<>(new SeatHoldReply(seatHold.getId(),
                    seatHold.getCustomerEmail(), getListOfSeatHoldVenueDetails(seatHold)), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.OK);
        }

        LOGGER.debug("Response for seat holds with input request {} is {}", seatHoldRequest, response);
        return response;
    }

    /**
     *
     * @param seatBookingRequest Request contains Hold id, customerEmail
     * @return SeatBookingReply
     */
    @RequestMapping(value = "/bookSeats", method = RequestMethod.POST)
    public ResponseEntity<SeatBookingReply> reserveSeats(@RequestBody final SeatBookingRequest seatBookingRequest){
        LOGGER.debug("Received request for reservation {}", seatBookingRequest);
        ResponseEntity<SeatBookingReply> response;
        final String confirmationCode = ticketService.reserveSeats(seatBookingRequest.getSeatHoldId(), seatBookingRequest.getCustomerEmail());
        if(StringUtils.isNotEmpty(confirmationCode)){
            response = new ResponseEntity<SeatBookingReply>(new SeatBookingReply(Integer.valueOf(seatBookingRequest.getSeatHoldId()),
                    seatBookingRequest.getCustomerEmail(), confirmationCode), HttpStatus.OK);
        } else{
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.debug("Finished request for reservation and the details are {}", seatBookingRequest, response);
        return response;
    }

    private List<SeatHoldVenueDetail> getListOfSeatHoldVenueDetails(SeatHold seatHold) {
        return TicketUtils.convertList(seatHold.getSeatBookings(), seatBooking -> {
            SeatHoldVenueDetail seatHoldVenueDetail = new SeatHoldVenueDetail();
            seatHoldVenueDetail.setLevel(seatBooking.getVenueId());
            seatHoldVenueDetail.setNumberOfSeatHolds(seatBooking.getNumberOfSeats());
            return seatHoldVenueDetail;
        });
    }
}
