package com.dev.parkinglot.model;

import java.util.List;

public record ParkingLot(String id, List<Floor> floors) {

    public Floor getFloor(int floorNumber) {
        if (floorNumber < 1 || floorNumber > floors.size()) {
            throw new IllegalArgumentException("Invalid floor number");
        }
        return floors.get(floorNumber - 1);
    }

}
