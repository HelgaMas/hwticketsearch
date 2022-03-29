package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;


public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket[] searchBy(String text, String text2) {
        Ticket[] result = new Ticket[0];
        for (Ticket product : repository.findAll()) {
            if (matches(product, text2)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Ticket product, String search) {
        if (product instanceof Ticket) {
            Ticket ticket = (Ticket) product;

            if (ticket.getDepartureAirport().contains(search)
                    && ticket.getArrivalAirport().contains(search)) {
                return true;
            }
        }
        return false;
    }
}