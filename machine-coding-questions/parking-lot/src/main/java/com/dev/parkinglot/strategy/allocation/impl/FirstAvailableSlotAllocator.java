package com.dev.parkinglot.strategy.allocation.impl;

import com.dev.parkinglot.enums.VehicleType;
import com.dev.parkinglot.model.Floor;
import com.dev.parkinglot.model.ParkingLot;
import com.dev.parkinglot.model.Slot;
import com.dev.parkinglot.strategy.allocation.SlotAllocator;

import java.util.Optional;

public class FirstAvailableSlotAllocator implements SlotAllocator {

    @Override
    public Optional<Slot> findAvailableSlot(ParkingLot parkingLot, VehicleType vehicleType) {
        return parkingLot.floors().stream()
                .map(floor -> findFirstAvailableSlot(floor, vehicleType))
                .filter(Optional::isPresent)
                .findFirst()
                .orElse(Optional.empty());
    }

    private Optional<Slot> findFirstAvailableSlot(Floor floor, VehicleType vehicleType) {
        return floor.slots().stream()
                .filter(slot -> slot.getVehicleType().equals(vehicleType) && slot.isAvailable())
                .findFirst();
    }
}
