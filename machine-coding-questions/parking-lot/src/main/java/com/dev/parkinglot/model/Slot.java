package com.dev.parkinglot.model;

import com.dev.parkinglot.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Slot {
    private final String parkingLotId;
    private final int floorNumber;
    private final int slotNumber;
    private final VehicleType vehicleType;
    @Setter
    private boolean isOccupied;

    public Slot(String parkingLotId, int floorNumber, int slotNumber, VehicleType vehicleType) {
        this.parkingLotId = parkingLotId;
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }
}

