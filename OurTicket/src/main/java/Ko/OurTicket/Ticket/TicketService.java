package Ko.OurTicket.Ticket;

import java.util.Map;

public interface TicketService {
    Map<Grade, Integer> countTicketByGradeForShow(Long showId);
}
