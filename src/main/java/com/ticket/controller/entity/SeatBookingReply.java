package com.ticket.controller.entity;

/**
* Reply object that contains reservation details for a seat booking
 */
public class SeatBookingReply {

    private final int seatHoldId;
    private final String customerEmail;
    private final String confirmationCode;

    public SeatBookingReply(int seatHoldId, String customerEmail, String confirmationCode) {
        this.seatHoldId = seatHoldId;
        this.customerEmail = customerEmail;
        this.confirmationCode = confirmationCode;
    }

    public int getSeatHoldId() {
        return seatHoldId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    @Override
    public String toString() {
        return "SeatBookingReply{" +
                "seatHoldId=" + seatHoldId +
                ", customerEmail='" + customerEmail + '\'' +
                ", confirmationCode='" + confirmationCode + '\'' +
                '}';
    }
}
