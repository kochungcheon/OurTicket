package ko.ourticket.purchase;

import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import ko.ourticket.account.AccountService;
import ko.ourticket.memberticket.MemberTicketService;
import ko.ourticket.ticket.TicketService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseFacadeService {
	private final TicketService ticketService;
	private final AccountService accountService;
	private final MemberTicketService memberTicketService;

	@Transactional
	public void purchaseTicket(final String nickName, final Long ticketId,
		final Integer requestSeatCount) {
		Integer totalPaymentAmount = ticketService.calculateTicketPrice(ticketId, requestSeatCount);
		ticketService.deductSeatsAfterPayment(ticketId, requestSeatCount);
		accountService.deductAmountOnPayment(nickName, totalPaymentAmount);
		memberTicketService.registerTicketForMember(nickName, ticketId, requestSeatCount);
	}
}
