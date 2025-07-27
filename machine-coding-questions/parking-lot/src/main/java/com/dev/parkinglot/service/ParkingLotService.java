package com.dev.parkinglot.service;

import com.dev.parkinglot.enums.DisplayType;
import com.dev.parkinglot.enums.ParkingLotCreatorType;
import com.dev.parkinglot.enums.SlotAllocationType;
import com.dev.parkinglot.enums.VehicleType;
import com.dev.parkinglot.model.ParkingLot;
import com.dev.parkinglot.model.Ticket;
import com.dev.parkinglot.model.Vehicle;
import com.dev.parkinglot.model.request.ParkingLotInitializationRequest;

public interface ParkingLotService {

    ParkingLot createParkingLot(ParkingLotInitializationRequest parkingLotInitializationRequest, ParkingLotCreatorType parkingLotCreatorType);

    Ticket parkVehicle(String parkingLotId, Vehicle vehicle, SlotAllocationType slotAllocationType);

    void unParkVehicle(String ticketId);

    void displayParkingLotStatus(String parkingLotId, VehicleType vehicleType, DisplayType displayType);
}
