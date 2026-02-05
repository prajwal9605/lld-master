package com.dev.parkinglot.command;

import java.util.List;

public interface CommandHandler {

    String getName();

    String execute(List<String> args);
}
