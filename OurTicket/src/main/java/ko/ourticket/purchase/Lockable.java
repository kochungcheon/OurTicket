package ko.ourticket.purchase;

@FunctionalInterface
public interface Lockable {
	void lock(Long ticketId, Runnable runnable);
}
