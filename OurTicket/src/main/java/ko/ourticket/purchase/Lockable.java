package ko.ourticket.purchase;

@FunctionalInterface
public interface Lockable {
	void lock(Runnable runnable);
}
