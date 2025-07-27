package com.dev.parkinglot.model.request;

import com.dev.parkinglot.enums.VehicleType;

public record SlotInitializationRequest(VehicleType vehicleType, int numberOfSlots) {
}
