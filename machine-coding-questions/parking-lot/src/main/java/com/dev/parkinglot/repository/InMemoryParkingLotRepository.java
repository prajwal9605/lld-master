package com.dev.parkinglot.repository;

import com.dev.parkinglot.model.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class InMemoryParkingLotRepository implements ParkingLotRepository {

    private final Map<String, ParkingLot> parkingLotMap;

    public InMemoryParkingLotRepository() {
        this.parkingLotMap = new HashMap<>();
    }

    @Override
    public ParkingLot getById(String id) {
        return parkingLotMap.get(id);
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        parkingLotMap.put(parkingLot.id(), parkingLot);
        return parkingLot;
    }
}
