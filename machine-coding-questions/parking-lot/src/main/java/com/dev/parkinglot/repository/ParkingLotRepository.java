package com.dev.parkinglot.repository;

import com.dev.parkinglot.model.ParkingLot;

public interface ParkingLotRepository {

    ParkingLot getById(String id);

    ParkingLot save(ParkingLot parkingLot);
}
