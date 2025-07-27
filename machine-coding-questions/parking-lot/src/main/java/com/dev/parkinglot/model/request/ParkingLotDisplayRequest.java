package com.dev.parkinglot.model.request;

import com.dev.parkinglot.enums.DisplayType;

public record ParkingLotDisplayRequest(String parkingLotId, DisplayType displayType) {
}
