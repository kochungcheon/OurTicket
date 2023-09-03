package ko.ourticket.ticket;

import ko.ourticket.performance.Performance;
import ko.ourticket.performance.PerformanceRepository;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService{
    private final TicketRepository ticketRepository;

    public GradeCount countTicketByGradeForPerformance(Performance performance) {
        List<Long> ticketIds = performance.getTicketIds();
        List<Ticket> tickets = ticketRepository.findAllById(ticketIds);
        return GradeCount.from(tickets);
    }
}
