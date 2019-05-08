package ejercicio1b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Monitor implements Runnable {
	private BlockingQueue<Message>[] queues;
	private final int s;
	private final int m;
	private List<Message> messages = new ArrayList<>();
	private volatile boolean exit = false;
	
	public Monitor(BlockingQueue<Message>[] queues, int s, int m) {
		this.queues = queues;
		this.s = s;
		this.m = m;
	}

	@Override
	public void run() {
		Message[] values = new Message[queues.length];
		
		while (!exit) {
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			int total = 0;
			try {
				for (int i = 0; i < values.length; i++) {
					values[i] = queues[i].take();
					messages.add(values[i]);
					int value = values[i].getValue();
					System.out.println(String.format("Value %d read from sensor %d at %d", value, values[i].getThread(), values[i].getTimestamp()));
					if (value > max) {
						max = value;
					}
					else {
						if (value < min) {
							min = value;
						}
					}
					total = total + value;
				}
				
				if (max - min > s) {
					System.out.println(String.format("Error en diferencia: %d - %d > %d", max, min, s));
				}
				
				int promedio = total / values.length;
				if (promedio > m) {
					System.out.println(String.format("Error en promedio: %d > %d", promedio, m));
				}
				
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void stop() {
		exit = true;
	}

}
