package com.concurrent.locks;

public class LockProcessFacktory {

	public static LockProcess createLockProcess(boolean synchronizedProcess, long readSleep, long writeSleep) {
		if (synchronizedProcess) {
			return new SynchronizedProcess(readSleep, writeSleep);
		} else {
			return new AtomicLockProcess(readSleep, writeSleep);
		}
	}
}
