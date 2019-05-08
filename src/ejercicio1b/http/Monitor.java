package ejercicio1b.http;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import ejercicio1b.http.Message;

public class Monitor implements Runnable {
	private BlockingQueue<Message>[] queues;
	private final int s;
	private final int m;
	private List<Message> messages = new ArrayList<>();
	
	public Monitor(BlockingQueue<Message>[] queues, int s, int m) {
		this.queues = queues;
		this.s = s;
		this.m = m;
	}

	@Override
	public void run() {
		Message[] values = new Message[queues.length];
		
		while (true) {
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
					System.out.println(String.format("Error: %d - %d > %d", max, min, s));
				}
				
				int promedio = total / values.length;
				if (promedio > m) {
					System.out.println(String.format("Error: %d > %d", promedio, m));
				}
				
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
