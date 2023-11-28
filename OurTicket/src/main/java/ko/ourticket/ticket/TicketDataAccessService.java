package ko.ourticket.ticket;

import java.util.List;

import org.springframework.stereotype.Service;

import ko.ourticket.performance.Performance;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketDataAccessService implements TicketReader, TicketWriter {
	private final TicketRepository ticketRepository;

	public Ticket findTicketBy(Long ticketId) {
		return ticketRepository.findById(ticketId)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다 상품Id : " + ticketId));
	}

	public List<Ticket> findTicketsBy(List<Long> ticketIds) {
		return ticketRepository.findAllById(ticketIds);
	}

	public void saveTicket(Long ticketId) {
		Ticket ticket = findTicketBy(ticketId);
		ticketRepository.save(ticket);
	}

	public Seat getSeat(Long ticketId) {
		Ticket ticket = findTicketBy(ticketId);
		return ticket.getSeat();
	}

	public GradeCount countTicketByGradeFor(Performance performance) {
		return GradeCount.from(ticketRepository.findAllById(performance.getTicketIds()));
	}

	public Ticket findTicketWithLockBy(Long ticketId) {
		return ticketRepository.findByIdForUpdate(ticketId).orElseThrow(
			() -> new RuntimeException("존재하지 않는 티켓입니다.")
		);
	}
}
