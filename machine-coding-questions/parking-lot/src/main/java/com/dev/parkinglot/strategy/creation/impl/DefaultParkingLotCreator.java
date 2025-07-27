package com.dev.parkinglot.strategy.creation.impl;

import com.dev.parkinglot.model.Floor;
import com.dev.parkinglot.model.ParkingLot;
import com.dev.parkinglot.model.Slot;
import com.dev.parkinglot.model.request.ParkingLotInitializationRequest;
import com.dev.parkinglot.model.request.SlotInitializationRequest;
import com.dev.parkinglot.strategy.creation.ParkingLotCreator;

import java.util.ArrayList;
import java.util.List;

public class DefaultParkingLotCreator implements ParkingLotCreator {

    @Override
    public ParkingLot initialize(ParkingLotInitializationRequest parkingLotInitializationRequest) {
        List<Floor> floors = new ArrayList<>();
        for (int floorNumber = 1; floorNumber < parkingLotInitializationRequest.numberOfFloors(); floorNumber++) {
            List<Slot> slots = new ArrayList<>();
            int slotNumber = 1;
            for (SlotInitializationRequest slotInitializationRequest : parkingLotInitializationRequest.slotInitializationRequests()) {
                for (int i = 0; i < slotInitializationRequest.numberOfSlots(); i++) {
                    slots.add(new Slot(parkingLotInitializationRequest.parkingLotId(), floorNumber, slotNumber++, slotInitializationRequest.vehicleType()));
                }
            }
            floors.add(new Floor(parkingLotInitializationRequest.parkingLotId(), floorNumber, slots));
        }
        return new ParkingLot(parkingLotInitializationRequest.parkingLotId(), floors);
    }
}
