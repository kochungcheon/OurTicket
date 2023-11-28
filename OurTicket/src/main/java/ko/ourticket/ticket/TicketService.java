package ko.ourticket.ticket;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {
	private final TicketWriter ticketWriter;
	private final TicketReader ticketReader;

	public void deductSeatsAfterPayment(Long ticketId, Integer requestSeatCount) {
		Seat seat = ticketReader.getSeat(ticketId);
		seat.reserveSeat(requestSeatCount);
		ticketWriter.saveTicket(ticketId);
	}

	public Integer calculateTicketPrice(Long ticketId, Integer requestSeatCount) {
		Ticket ticket = ticketReader.findTicketBy(ticketId);
		return ticket.getFixedPrice() * requestSeatCount;
	}
}
