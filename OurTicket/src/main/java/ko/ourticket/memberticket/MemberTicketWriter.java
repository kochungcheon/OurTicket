package ko.ourticket.memberticket;

public interface MemberTicketWriter {
	void assignSeatsToMember(Long memberId, Long ticketId,
		Integer ticketPrice, Integer requestSeatCount);
}
