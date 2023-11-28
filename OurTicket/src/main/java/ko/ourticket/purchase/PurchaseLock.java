package ko.ourticket.purchase;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PurchaseLock implements Lockable {
	private ReentrantLock lock = new ReentrantLock(true);

	public void lock(Runnable runnable) {
		lock.lock();
		try {
			runnable.run();
		} finally {
			lock.unlock();
		}
		;
	}
}
