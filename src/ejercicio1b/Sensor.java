package ejercicio1b;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Sensor implements Runnable {
	private int id;
	private BlockingQueue<Message> queue;
	
	public Sensor(int id, BlockingQueue<Message> queue) {
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		Random random = new Random();
		while (true) {
			int value = random.nextInt(100);
			try {
				queue.put(new Message(id, value));
				System.out.println(String.format("Value %d measured by %d", value, id));
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
