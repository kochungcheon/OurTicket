package Ko.OurTicket.Ticket;

import Ko.OurTicket.Performance.Performance;
import Ko.OurTicket.Performance.PerformanceRepository;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService{
    private TicketRepository ticketRepository;
    private PerformanceRepository performanceRepository;

    public TicketServiceImpl(final TicketRepository ticketRepository,
                             final PerformanceRepository performanceRepository) {
        this.ticketRepository = ticketRepository;
        this.performanceRepository = performanceRepository;
    }

    @Override
    public Map<Grade, Integer> countTicketByGradeForShow(Long showId) {
        Performance performance = performanceRepository.findById(showId).orElseThrow();
        List<Ticket> tickets = performance.getTicketList();
        return tickets.stream()
                .collect(Collectors.groupingBy(
                        Ticket::getGrade,
                        () -> new EnumMap<>(Grade.class),
                        Collectors.summingInt(Ticket::getSeatCount)
                ));
    }
}
