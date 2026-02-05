package com.dev.parkinglot.model.request;

import java.util.List;

public record ParkingLotInitializationRequest(String parkingLotId, List<FloorInitializationRequest> floors) {
}
