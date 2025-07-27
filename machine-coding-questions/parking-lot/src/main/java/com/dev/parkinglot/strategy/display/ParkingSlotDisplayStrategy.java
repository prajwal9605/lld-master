package com.dev.parkinglot.strategy.display;

import com.dev.parkinglot.enums.VehicleType;
import com.dev.parkinglot.model.ParkingLot;

public interface ParkingSlotDisplayStrategy {

    void display(ParkingLot parkingLot, VehicleType vehicleType);
}
