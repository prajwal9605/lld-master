package com.dev.parkinglot.factory;

import com.dev.parkinglot.enums.ParkingLotCreatorType;
import com.dev.parkinglot.strategy.creation.ParkingLotCreator;
import com.dev.parkinglot.strategy.creation.impl.DefaultParkingLotCreator;

public class ParkingLotCreatorFactory {
    DefaultParkingLotCreator defaultParkingLotCreator = new DefaultParkingLotCreator();

    public ParkingLotCreator getParkingLotCreator(ParkingLotCreatorType parkingLotCreatorType) {
        return switch (parkingLotCreatorType) {
            case DEFAULT -> defaultParkingLotCreator;
        };
    }
}
