package ko.ourticket.ticket;

import ko.ourticket.performance.Performance;

public interface TicketReader {
	Ticket findTicketBy(Long ticketId);

	Seat getSeat(Long ticketId);

	GradeCount countTicketByGradeFor(Performance performance);

	Ticket findTicketWithLockBy(Long ticketId);
}
