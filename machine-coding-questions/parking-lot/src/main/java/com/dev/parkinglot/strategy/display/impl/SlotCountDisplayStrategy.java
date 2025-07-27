package com.dev.parkinglot.strategy.display.impl;

import com.dev.parkinglot.enums.VehicleType;
import com.dev.parkinglot.model.ParkingLot;
import com.dev.parkinglot.model.Slot;
import com.dev.parkinglot.strategy.display.ParkingSlotDisplayStrategy;

import java.util.function.Predicate;

public class SlotCountDisplayStrategy implements ParkingSlotDisplayStrategy {

    private final boolean showFreeSlotCount;

    public SlotCountDisplayStrategy(boolean showFreeSlotCount) {
        this.showFreeSlotCount = showFreeSlotCount;
    }

    @Override
    public void display(ParkingLot parkingLot, VehicleType vehicleType) {
        parkingLot.floors().forEach(floor -> {
            Predicate<Slot> filterPredicate = showFreeSlotCount ? Slot::isAvailable : Slot::isOccupied;
            long slotCount = floor.slots().stream()
                    .filter(filterPredicate)
                    .count();

            String slotType = showFreeSlotCount ? "free" : "occupied";
            System.out.println(String.format("No. of %s slots for %s on Floor %d: %d", slotType, vehicleType, floor.floorNumber(), slotCount));
        });
    }
}
