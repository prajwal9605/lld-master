package com.dev.parkinglot.command.impl;

import com.dev.parkinglot.command.CommandHandler;
import com.dev.parkinglot.exception.NotFoundException;
import com.dev.parkinglot.model.Ticket;
import com.dev.parkinglot.service.ParkingLotService;

import java.util.List;

public class UnParkVehicleCommandHandler implements CommandHandler {

    private final ParkingLotService parkingLotService;

    public UnParkVehicleCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String getName() {
        return "unpark_vehicle";
    }

    @Override
    public String execute(List<String> args) {
        String ticketId = args.getFirst();
        try {
            Ticket ticket = parkingLotService.unParkVehicle(ticketId);
            return String.format("Unparked vehicle with Registration Number: %s and Color: %s", ticket.vehicle().registrationNumber(),
                    ticket.vehicle().color());
        } catch (NotFoundException e) {
            return "Invalid Ticket";
        }
    }
}
