package ko.ourticket.performance;

import org.springframework.stereotype.Service;

import ko.ourticket.ticket.GradeCount;
import ko.ourticket.ticket.TicketReader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketCountFacadeService {
	private final PerformanceReader performanceReader;
	private final TicketReader ticketReader;

	public GradeCount countTicketByGradeForPerformance(Long performanceId) {
		Performance performance = performanceReader.findBy(performanceId);
		return ticketReader.countTicketByGradeFor(performance);
	}
}
