package com.dev.parkinglot.strategy.allocation;

import com.dev.parkinglot.enums.VehicleType;
import com.dev.parkinglot.model.ParkingLot;
import com.dev.parkinglot.model.Slot;

import java.util.Optional;

public interface SlotAllocator {
    Optional<Slot> findAvailableSlot(ParkingLot parkingLot, VehicleType vehicleType);
}
