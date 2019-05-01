package ejercicio1b;

import java.util.Date;

public class Message {
	private final int value;
	private final long timestamp;
	private final int thread;
	
	public Message(int thread, int value) {
		this.thread = thread;
		this.value = value;
		this.timestamp = new Date().getTime();
	}

	public int getValue() {
		return value;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getThread() {
		return thread;
	}
}
