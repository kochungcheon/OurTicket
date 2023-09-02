package ko.ourticket.ticket;

import ko.ourticket.performance.Performance;
import ko.ourticket.performance.PerformanceRepository;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TicketService{
    private TicketRepository ticketRepository;

    public TicketService(final TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public GradeCount countTicketByGradeForPerformance(Long performanceId) {
        List<Ticket> tickets = ticketRepository.findByPerformanceId(performanceId);
        return GradeCount.from(tickets);
    }
}
