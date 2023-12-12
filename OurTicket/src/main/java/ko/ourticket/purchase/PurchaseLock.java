package ko.ourticket.purchase;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

@Component
public class PurchaseLock implements Lockable {
	private final ConcurrentHashMap<Long, ReentrantLock> lockMap = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<Long, Long> timestampMap = new ConcurrentHashMap<>();
	private final ScheduledExecutorService ticketLockChecker = Executors.newScheduledThreadPool(1);

	private PurchaseLock() {
		ticketLockChecker.scheduleAtFixedRate(this::cleanUp, 0, 6, TimeUnit.HOURS);
	}

	public void lock(Long ticketId, Runnable runnable) {
		ReentrantLock lock = lockMap.computeIfAbsent(ticketId, k -> new ReentrantLock(true));
		timestampMap.put(ticketId, System.currentTimeMillis());
		lock.lock();
		try {
			runnable.run();
		} finally {
			lock.unlock();
			timestampMap.put(ticketId, System.currentTimeMillis());
		}
	}

	private void cleanUp() {
		long threshold = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(6);
		for (Long ticketId : timestampMap.keySet()) {
			if (timestampMap.get(ticketId) < threshold) {
				ReentrantLock lock = lockMap.get(ticketId);
				if (lock != null && !lock.isLocked()) {
					lockMap.remove(ticketId);
					timestampMap.remove(ticketId);
				}
			}
		}
	}
}

