package com.dev.parkinglot.factory;

import com.dev.parkinglot.enums.DisplayType;
import com.dev.parkinglot.strategy.display.ParkingSlotDisplayStrategy;
import com.dev.parkinglot.strategy.display.impl.FreeOrOccupiedSlotDisplayStrategy;
import com.dev.parkinglot.strategy.display.impl.SlotCountDisplayStrategy;

public class ParkingLotDisplayFactory {

    private final FreeOrOccupiedSlotDisplayStrategy freeSlotDisplayStrategy = new FreeOrOccupiedSlotDisplayStrategy(true);
    private final FreeOrOccupiedSlotDisplayStrategy occupiedSlotDisplayStrategy = new FreeOrOccupiedSlotDisplayStrategy(false);
    private final SlotCountDisplayStrategy freeSlotCountDisplayStrategy = new SlotCountDisplayStrategy(true);

    public ParkingSlotDisplayStrategy getByDisplayType(DisplayType displayType) {
        return switch (displayType) {
            case FREE_SLOTS_PER_FLOOR -> freeSlotDisplayStrategy;
            case OCCUPIED_SLOTS_PER_FLOOR -> occupiedSlotDisplayStrategy;
            case FREE_SLOT_COUNT_PER_FLOOR -> freeSlotCountDisplayStrategy;
        };
    }
}
