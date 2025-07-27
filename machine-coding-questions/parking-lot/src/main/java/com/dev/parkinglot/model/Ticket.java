package com.dev.parkinglot.model;

public record Ticket(Slot slot, Vehicle vehicle) {

    public String getTicketId() {
        return slot.getParkingLotId() + "_" + slot.getFloorNumber() + "_" + slot.getSlotNumber();
    }
}
