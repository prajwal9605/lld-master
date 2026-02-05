package com.dev.parkinglot.command.impl;

import com.dev.parkinglot.command.CommandHandler;
import com.dev.parkinglot.enums.ParkingLotCreatorType;
import com.dev.parkinglot.enums.VehicleType;
import com.dev.parkinglot.exception.BadRequestException;
import com.dev.parkinglot.model.request.FloorInitializationRequest;
import com.dev.parkinglot.model.request.ParkingLotInitializationRequest;
import com.dev.parkinglot.model.request.SlotInitializationRequest;
import com.dev.parkinglot.service.ParkingLotService;

import java.util.ArrayList;
import java.util.List;

public class CreateParkingLotCommandHandler implements CommandHandler {
    private final ParkingLotService parkingLotService;

    public CreateParkingLotCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String getName() {
        return "create_parking_lot";
    }

    @Override
    public String execute(List<String> args) {
        String parkingLotId = args.get(0);
        int numberOfFloors = parseInt(args.get(1));
        if (numberOfFloors <= 0) {
            throw new BadRequestException("Expected number of floors to be greater than zero");
        }
        int numberOfSlots = parseInt(args.get(2));
        if (numberOfSlots <= 0) {
            throw new BadRequestException("Expected number of slots to be greater than zero");
        }
        ParkingLotInitializationRequest parkingLotInitializationRequest = getParkingLotInitializationRequest(
                numberOfFloors, numberOfSlots, parkingLotId);
        parkingLotService.createParkingLot(parkingLotInitializationRequest,
                ParkingLotCreatorType.DEFAULT);

        return String.format("Created parking lot with %s floors and %s slots per floor", numberOfFloors, numberOfSlots);
    }

    private static ParkingLotInitializationRequest getParkingLotInitializationRequest(int numberOfFloors, int numberOfSlots, String parkingLotId) {
        List<FloorInitializationRequest> floorRequests = new ArrayList<>();
        for (int i = 0; i < numberOfFloors; i++) {
            int trucks = 1;
            int bikes = Math.max(0, Math.min(numberOfSlots - trucks, 2));
            int cars = Math.max(0, numberOfSlots - trucks - bikes);
            List<SlotInitializationRequest> slotRequests = List.of(
                    new SlotInitializationRequest(VehicleType.TRUCK, trucks),
                    new SlotInitializationRequest(VehicleType.BIKE, bikes),
                    new SlotInitializationRequest(VehicleType.CAR, cars)
            );
            floorRequests.add(new FloorInitializationRequest(i + 1, slotRequests));
        }
        return new ParkingLotInitializationRequest(parkingLotId,
                floorRequests);
    }

    private int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new BadRequestException(String.format("Expected %s to be integer", number));
        }
    }
}
