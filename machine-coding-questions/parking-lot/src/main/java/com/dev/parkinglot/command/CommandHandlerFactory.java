package com.dev.parkinglot.command;

import com.dev.parkinglot.command.impl.CreateParkingLotCommandHandler;
import com.dev.parkinglot.command.impl.ExitCommandHandler;
import com.dev.parkinglot.command.impl.ParkVehicleCommandHandler;
import com.dev.parkinglot.command.impl.UnParkVehicleCommandHandler;
import com.dev.parkinglot.exception.BadRequestException;
import com.dev.parkinglot.service.ParkingLotService;

import java.util.List;

public class CommandHandlerFactory {
    private final List<CommandHandler> commandHandlers;

    public CommandHandlerFactory(ParkingLotService parkingLotService) {
        this.commandHandlers = List.of(
                new CreateParkingLotCommandHandler(parkingLotService),
                new ExitCommandHandler(),
                new ParkVehicleCommandHandler(parkingLotService),
                new UnParkVehicleCommandHandler(parkingLotService)
        );
    }

    public CommandHandler getCommandHandler(String commandLine) {
        if (commandLine == null || commandLine.isEmpty()) {
            throw new BadRequestException("Empty command line received");
        }
        String[] parts = commandLine.split("\\s+");
        String commandName = parts[0];

        return commandHandlers.stream()
                .filter(commandHandler -> commandHandler.getName().equals(commandName))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(String.format("Unsupported command name %s", commandName)));
    }
}
