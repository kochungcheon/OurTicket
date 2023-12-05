package ko.ourticket.purchase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PurchaseController {
	private final PurchaseTicketLock purchaseTicketLock;

	@PostMapping("/api/tickets/ticketReserve")
	public ResponseEntity<String> reserveTicket(@RequestBody PurchaseRequest purchaseRequest) {
		purchaseTicketLock.purchaseInOrder(
			purchaseRequest.nickName(),
			purchaseRequest.ticketId(),
			purchaseRequest.requestSeatCount());
		return ResponseEntity.noContent().build();
	}
}
