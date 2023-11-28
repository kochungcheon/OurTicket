package ko.ourticket.memberticket;

import org.springframework.stereotype.Service;

import ko.ourticket.member.Member;
import ko.ourticket.member.MemberReader;
import ko.ourticket.ticket.Ticket;
import ko.ourticket.ticket.TicketDataAccessService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberTicketService {
	private final MemberTicketWriter memberTicketWriter;
	private final MemberReader memberReader;
	private final TicketDataAccessService ticketDataAccessService;

	public void registerTicketForMember(String nickName, Long ticketId, Integer requestSeatCount) {
		Member member = memberReader.findMemberBy(nickName);
		Ticket ticket = ticketDataAccessService.findTicketBy(ticketId);
		memberTicketWriter.assignSeatsToMember(member.getId(), ticket.getId(),
			ticket.getFixedPrice(), requestSeatCount);
	}
}
