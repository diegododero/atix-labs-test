package ejercicio1b.http;

public class Message {
	private final int value;
	private final long timestamp;
	private final int thread;
	
	public Message(int thread, int value, long timestamp) {
		this.thread = thread;
		this.value = value;
		this.timestamp = timestamp;
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
