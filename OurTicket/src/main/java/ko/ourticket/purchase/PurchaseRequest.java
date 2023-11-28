package ko.ourticket.purchase;

public record PurchaseRequest(String nickName,
							  Long ticketId,
							  Integer requestSeatCount) {
	public static PurchaseRequest of
		(
			String nickName,
			Long ticketId,
			Integer requestSeatCount
		) {
		return new PurchaseRequest
			(
				nickName,
				ticketId,
				requestSeatCount
			);
	}
}
