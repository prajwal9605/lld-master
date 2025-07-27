package com.dev.parkinglot.model;

import java.util.List;

public record Floor(String parkingLotId, int floorNumber, List<Slot> slots) {

}
