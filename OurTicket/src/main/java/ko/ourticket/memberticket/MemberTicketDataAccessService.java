package ko.ourticket.memberticket;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberTicketDataAccessService implements MemberTicketWriter {
	private final MemberTicketRepository memberTicketRepository;

	public void assignSeatsToMember(Long memberId, Long ticketId,
		Integer ticketPrice, Integer requestSeatCount) {
		MemberTicket memberTicket = MemberTicket.of(memberId, ticketId,
			ticketPrice, requestSeatCount);
		memberTicketRepository.save(memberTicket);
	}
}
