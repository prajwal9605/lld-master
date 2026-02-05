package com.dev.parkinglot.command.impl;

import com.dev.parkinglot.command.CommandHandler;
import com.dev.parkinglot.enums.SlotAllocationType;
import com.dev.parkinglot.enums.VehicleType;
import com.dev.parkinglot.exception.EmptySlotNotFoundException;
import com.dev.parkinglot.model.Ticket;
import com.dev.parkinglot.model.Vehicle;
import com.dev.parkinglot.service.ParkingLotService;

import java.util.List;

public class ParkVehicleCommandHandler implements CommandHandler {
    private final ParkingLotService parkingLotService;

    public ParkVehicleCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String getName() {
        return "park_vehicle";
    }

    @Override
    public String execute(List<String> args) {
        String parkingLotId = args.get(0);
        VehicleType vehicleType = VehicleType.valueOf(args.get(1));
        String registrationNo = args.get(2);
        Vehicle vehicle = new Vehicle(registrationNo, vehicleType, null);
        try {
            Ticket ticket = parkingLotService.parkVehicle(parkingLotId, vehicle, SlotAllocationType.FIRST_AVAILABLE);
            return String.format("Parked vehicle. Ticket ID: %s", ticket.getTicketId());
        } catch (EmptySlotNotFoundException e) {
            return e.getMessage();
        }
    }
}
