package com.dev.parkinglot.model.request;

import java.util.List;

// Can it be an interface?
public record ParkingLotInitializationRequest(String parkingLotId, int numberOfFloors,
                                              List<SlotInitializationRequest> slotInitializationRequests) {
}
