package com.dev.parkinglot.model.request;

import java.util.List;

public record FloorInitializationRequest(int floorNumber, List<SlotInitializationRequest> slots) {
}
