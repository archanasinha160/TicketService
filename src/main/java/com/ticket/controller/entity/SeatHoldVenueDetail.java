package com.ticket.controller.entity;

/**
 * Entity to store Venue details.
 */
public class SeatHoldVenueDetail {

    private int level;
    private int numberOfSeatHolds;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumberOfSeatHolds() {
        return numberOfSeatHolds;
    }

    public void setNumberOfSeatHolds(int numberOfSeatHolds) {
        this.numberOfSeatHolds = numberOfSeatHolds;
    }

    @Override
    public String toString() {
        return "SeatHoldVenueDetail{" +
                "level=" + level +
                ", numberOfSeatHolds=" + numberOfSeatHolds +
                '}';
    }
}
