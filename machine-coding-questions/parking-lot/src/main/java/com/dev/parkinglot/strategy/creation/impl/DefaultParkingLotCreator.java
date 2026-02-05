package com.dev.parkinglot.strategy.creation.impl;

import com.dev.parkinglot.model.Floor;
import com.dev.parkinglot.model.ParkingLot;
import com.dev.parkinglot.model.Slot;
import com.dev.parkinglot.model.request.FloorInitializationRequest;
import com.dev.parkinglot.model.request.ParkingLotInitializationRequest;
import com.dev.parkinglot.model.request.SlotInitializationRequest;
import com.dev.parkinglot.strategy.creation.ParkingLotCreator;

import java.util.ArrayList;
import java.util.List;

public class DefaultParkingLotCreator implements ParkingLotCreator {

    @Override
    public ParkingLot initialize(ParkingLotInitializationRequest parkingLotInitializationRequest) {
        List<Floor> floors = new ArrayList<>();
        for (FloorInitializationRequest floorRequest : parkingLotInitializationRequest.floors()) {
            List<Slot> slots = new ArrayList<>();
            int slotNumber = 1;
            for (SlotInitializationRequest slotInitializationRequest : floorRequest.slots()) {
                for (int i = 0; i < slotInitializationRequest.numberOfSlots(); i++) {
                    slots.add(new Slot(parkingLotInitializationRequest.parkingLotId(), floorRequest.floorNumber(), slotNumber++, slotInitializationRequest.vehicleType()));
                }
            }
            floors.add(new Floor(parkingLotInitializationRequest.parkingLotId(), floorRequest.floorNumber(), slots));
        }
        return new ParkingLot(parkingLotInitializationRequest.parkingLotId(), floors);
    }
}
