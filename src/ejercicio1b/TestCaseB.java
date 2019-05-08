package ejercicio1b;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestCaseB {

	public static void main(String[] args) {
		int threadsNumber = 4;
		Sensor[] sensors = new Sensor[threadsNumber];
		BlockingQueue<Message>[] queues = (BlockingQueue<Message>[]) new BlockingQueue[threadsNumber];
		
		for (int i = 0; i < threadsNumber; i++) {
			queues[i] = new LinkedBlockingQueue<Message>();
			sensors[i] = new Sensor(i, queues[i]);
			
			Thread thread = new Thread(sensors[i]);
			thread.start();

		}
		
		Monitor monitor = new Monitor(queues, 10, 15);
		Thread monitorThread = new Thread(monitor);
		monitorThread.start();
	}

}
