package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager ticketManager = new TicketManager(repository);

    private final Ticket ticket1 = new Ticket(1, 200000, "GOJ", "DME", 80);
    private final Ticket ticket2 = new Ticket(2, 400000, "SVO", "LED", 90);
    private final Ticket ticket3 = new Ticket(3, 1200000, "TJM", "KUF", 390);
    private final Ticket ticket4 = new Ticket(4, 680000, "GOJ", "DME", 85);

    @BeforeEach
    public void saveToAll() {
        repository.save(ticket1);
        repository.save(ticket2);
        repository.save(ticket3);
        repository.save(ticket4);
    }

    @Test
    public void shouldSort() {
        Ticket[] expected = new Ticket[]{ticket1, ticket2, ticket4, ticket3};
        Ticket[] actual = new Ticket[]{ticket1, ticket2, ticket3, ticket4};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNoTickets() {

        Ticket[] expected = {};
        Ticket[] actual = ticketManager.searchBy("GOJ", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTickets() {
        ticketManager.searchBy("GOJ", "DME");

        Ticket[] expected = {ticket1, ticket4};
        Ticket[] actual = {ticket4, ticket1};
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}