package com.dev.parkinglot.repository;

import com.dev.parkinglot.model.Ticket;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTicketRepository implements TicketRepository {

    private final Map<String, Ticket> ticketMap;

    public InMemoryTicketRepository() {
        ticketMap = new HashMap<>();
    }

    @Override
    public Ticket getById(String id) {
        return ticketMap.get(id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        ticketMap.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    @Override
    public void delete(String id) {
        ticketMap.remove(id);
    }
}
