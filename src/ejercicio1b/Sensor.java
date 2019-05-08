package ejercicio1b;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Sensor implements Runnable {
	private int id;
	private BlockingQueue<Message> queue;
	private volatile boolean exit = false;
	private int totalMessages = 0;
	
	public Sensor(int id, BlockingQueue<Message> queue) {
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		Random random = new Random();
		while (!exit) {
			int value = random.nextInt(100);
			try {
				queue.put(new Message(id, value));
				totalMessages++;
				//System.out.println(String.format("Value %d measured by %d", value, id));
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void stop() {
		exit = true;
	}

	public int getTotalMessages() {
		return totalMessages;
	}


}
