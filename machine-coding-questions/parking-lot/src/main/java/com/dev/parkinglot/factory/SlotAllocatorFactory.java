package com.dev.parkinglot.factory;

import com.dev.parkinglot.enums.SlotAllocationType;
import com.dev.parkinglot.strategy.allocation.SlotAllocator;
import com.dev.parkinglot.strategy.allocation.impl.FirstAvailableSlotAllocator;

public class SlotAllocatorFactory {
    private final SlotAllocator firstAvailableSlotAllocator = new FirstAvailableSlotAllocator();

    public SlotAllocator getSlotAllocator(SlotAllocationType slotAllocationType) {
        return switch (slotAllocationType) {
            case FIRST_AVAILABLE -> firstAvailableSlotAllocator;
        };
    }
}
