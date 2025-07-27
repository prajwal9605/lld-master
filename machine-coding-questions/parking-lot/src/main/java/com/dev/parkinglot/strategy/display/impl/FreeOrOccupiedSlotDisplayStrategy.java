package com.dev.parkinglot.strategy.display.impl;

import com.dev.parkinglot.enums.VehicleType;
import com.dev.parkinglot.model.ParkingLot;
import com.dev.parkinglot.model.Slot;
import com.dev.parkinglot.strategy.display.ParkingSlotDisplayStrategy;

import java.util.function.Predicate;

public class FreeOrOccupiedSlotDisplayStrategy implements ParkingSlotDisplayStrategy {

    private final boolean showAvailableSlots;

    public FreeOrOccupiedSlotDisplayStrategy(boolean showAvailableSlots) {
        this.showAvailableSlots = showAvailableSlots;
    }

    @Override
    public void display(ParkingLot parkingLot, VehicleType vehicleType) {
        parkingLot.floors().forEach(floor -> {
            String slotType = showAvailableSlots ? "Free" : "Occupied";
            String displayString = String.format("%s slots for %s on Floor %d:", slotType, vehicleType, floor.floorNumber());
            Predicate<Slot> filterPredicate = showAvailableSlots ? Slot::isAvailable : Slot::isOccupied;
            displayString = displayString + String.join(", " + floor.slots().stream()
                    .filter(filterPredicate)
                    .map(Slot::getSlotNumber)
                    .toList());

            System.out.println(displayString);
        });
    }
}
