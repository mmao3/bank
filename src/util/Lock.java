package util;

import java.io.Serializable;
import java.util.Calendar;

public class Lock implements Serializable {

	boolean lock;
	long lockTime;

	public Lock() {

		lock = false;
		lockTime = 0;
	}

	public Lock(boolean lock, long lockTime) {
		this.lock = lock;
		this.lockTime = lockTime;

	}

	public boolean isLocked() {

		return lock;
	}

	public long getLockTime() {

		return lockTime;
	}

	public void addLock(long lockTime) {
		lock = true;
		this.lockTime = lockTime;

	}

	public void removeLock() {
		lock = false;
		lockTime = 0;

	}

	public String getLockTimeInDate() {
		Calendar calendar = Calendar.getInstance();
		if (lockTime > 0) {

			calendar.setTimeInMillis(lockTime);
			return calendar.get(Calendar.YEAR) + " " + (calendar.get(Calendar.MONTH) + 1) + " "
					+ calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.HOUR) + " "
					+ calendar.get(Calendar.MINUTE) + " " + calendar.get(Calendar.SECOND) + " "
					+ (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");

		} else

			return null;

	}

}