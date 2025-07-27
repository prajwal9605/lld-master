package com.dev.parkinglot.service.impl;

import com.dev.parkinglot.enums.DisplayType;
import com.dev.parkinglot.enums.ParkingLotCreatorType;
import com.dev.parkinglot.enums.SlotAllocationType;
import com.dev.parkinglot.enums.VehicleType;
import com.dev.parkinglot.exception.NotFoundException;
import com.dev.parkinglot.factory.ParkingLotCreatorFactory;
import com.dev.parkinglot.factory.ParkingLotDisplayFactory;
import com.dev.parkinglot.factory.SlotAllocatorFactory;
import com.dev.parkinglot.model.ParkingLot;
import com.dev.parkinglot.model.Ticket;
import com.dev.parkinglot.model.Vehicle;
import com.dev.parkinglot.model.request.ParkingLotInitializationRequest;
import com.dev.parkinglot.repository.InMemoryParkingLotRepository;
import com.dev.parkinglot.repository.InMemoryTicketRepository;
import com.dev.parkinglot.repository.ParkingLotRepository;
import com.dev.parkinglot.repository.TicketRepository;
import com.dev.parkinglot.service.ParkingLotService;

import java.util.Objects;

public class ParkingLotServiceImpl implements ParkingLotService {
    private final ParkingLotCreatorFactory parkingLotCreatorFactory;
    private final SlotAllocatorFactory slotAllocatorFactory;
    private final ParkingLotDisplayFactory parkingLotDisplayFactory;
    private final ParkingLotRepository parkingLotRepository;
    private final TicketRepository ticketRepository;

    public ParkingLotServiceImpl() {
        this.parkingLotCreatorFactory = new ParkingLotCreatorFactory();
        this.slotAllocatorFactory = new SlotAllocatorFactory();
        this.parkingLotDisplayFactory = new ParkingLotDisplayFactory();
        this.parkingLotRepository = new InMemoryParkingLotRepository();
        this.ticketRepository = new InMemoryTicketRepository();
    }

    @Override
    public ParkingLot createParkingLot(ParkingLotInitializationRequest parkingLotInitializationRequest, ParkingLotCreatorType parkingLotCreatorType) {
        ParkingLot parkingLot = parkingLotCreatorFactory.getParkingLotCreator(parkingLotCreatorType).initialize(parkingLotInitializationRequest);
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public Ticket parkVehicle(String parkingLotId, Vehicle vehicle, SlotAllocationType slotAllocationType) {
        ParkingLot parkingLot = getParkingLot(parkingLotId);
        return slotAllocatorFactory.getSlotAllocator(slotAllocationType)
                .findAvailableSlot(parkingLot, vehicle.vehicleType())
                .map(slot -> {
                    Ticket ticket = new Ticket(slot, vehicle);
                    ticket = ticketRepository.save(ticket);
                    slot.setOccupied(true);
                    return ticket;
                }).orElse(null);
    }

    private ParkingLot getParkingLot(String parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.getById(parkingLotId);
        if (Objects.isNull(parkingLot)) {
            throw new NotFoundException(String.format("Parking Lot not found by id %s", parkingLotId));
        }
        return parkingLot;
    }

    @Override
    public void unParkVehicle(String ticketId) {
        Ticket ticket = ticketRepository.getById(ticketId);
        if (Objects.isNull(ticket)) {
            throw new NotFoundException(String.format("Ticket not found with id %s", ticketId));
        }
        ticket.slot().setOccupied(false);
        ticketRepository.delete(ticketId);
    }

    @Override
    public void displayParkingLotStatus(String parkingLotId, VehicleType vehicleType, DisplayType displayType) {
        ParkingLot parkingLot = getParkingLot(parkingLotId);
        parkingLotDisplayFactory.getByDisplayType(displayType).display(parkingLot, vehicleType);
    }
}
