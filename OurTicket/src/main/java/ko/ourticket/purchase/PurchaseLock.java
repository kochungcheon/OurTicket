package ko.ourticket.purchase;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PurchaseLock implements Lockable {
	private ConcurrentHashMap<Long, ReentrantLock> lock = new ConcurrentHashMap<>();
	public void lock(Long ticketId, Runnable runnable) {
		System.out.println(ticketId);
		lock.computeIfAbsent(ticketId, k -> new ReentrantLock(true)).lock();
		try {
			runnable.run();
		} finally {
			lock.get(ticketId).unlock();
		};
	}
}
