package com.dev.parkinglot.repository;

import com.dev.parkinglot.model.Ticket;

public interface TicketRepository {

    Ticket getById(String id);

    Ticket save(Ticket ticket);

    void delete(String id);
}
