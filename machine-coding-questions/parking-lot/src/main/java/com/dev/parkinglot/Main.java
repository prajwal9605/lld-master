package com.dev.parkinglot;

import com.dev.parkinglot.command.CommandHandlerFactory;
import com.dev.parkinglot.service.impl.ParkingLotServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory(new ParkingLotServiceImpl());
        while (true) {
            String command = scanner.nextLine();
            try {
                String[] parts = command.split("\\s+");
                List<String> commandArgs;
                if (parts.length > 1) {
                    commandArgs = Arrays.stream(parts, 1, parts.length).toList();
                } else {
                    commandArgs = Collections.singletonList(parts[0]);
                }

                String output = commandHandlerFactory.getCommandHandler(command).execute(commandArgs);
                System.out.println(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
