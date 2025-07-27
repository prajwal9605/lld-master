package com.dev.parkinglot.model;

import com.dev.parkinglot.enums.Color;
import com.dev.parkinglot.enums.VehicleType;

public record Vehicle(String registrationNumber, VehicleType vehicleType, Color color) {
}
