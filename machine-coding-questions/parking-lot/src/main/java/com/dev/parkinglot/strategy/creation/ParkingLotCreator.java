package com.dev.parkinglot.strategy.creation;

import com.dev.parkinglot.model.ParkingLot;
import com.dev.parkinglot.model.request.ParkingLotInitializationRequest;

public interface ParkingLotCreator {

    ParkingLot initialize(ParkingLotInitializationRequest parkingLotInitializationRequest);

}
