package ejercicio1b;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestCaseA {
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
		
		// Los threads corren durante dos minutos
		try {
			TimeUnit.MINUTES.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin de la ejecución");
		
		for (int i = 0; i < threadsNumber; i++) {
			sensors[i].stop();
		}
		monitor.stop();
		
		// Durante dos minutos el monitor debe haber procesado al menos 16 mensajes
		// 4 datos de sensores * 2 * 2
		System.out.println(String.format("Mensajes procesados por el monitor %d", monitor.getMessages().size()));
		
		// Cada sensor debe haber enviado al menos 240 mensajes
		// 2 por segundo * 60 * 2
		for (int i = 0; i < threadsNumber; i++) {
			System.out.println(String.format("Mensajes generados por %d: %d", i, sensors[i].getTotalMessages()));
		}
	}
}
