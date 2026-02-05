package com.dev.parkinglot.command.impl;

import com.dev.parkinglot.command.CommandHandler;

import java.util.List;

public class ExitCommandHandler implements CommandHandler {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String execute(List<String> args) {
        System.exit(0);
        return "";
    }
}
