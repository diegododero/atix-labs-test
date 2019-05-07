package ejercicio1b.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sun.net.httpserver.HttpServer;

import ejercicio1b.http.Monitor;
import ejercicio1b.http.Message;
import ejercicio1b.http.Sensor;

public class Test {

	public static void main(String[] args) {
		int threadsNumber = 4;
		Sensor[] sensors = new Sensor[threadsNumber];
		BlockingQueue<Message>[] queues = (BlockingQueue<Message>[]) new BlockingQueue[threadsNumber];
		
		for (int i = 0; i < threadsNumber; i++) {
			queues[i] = new LinkedBlockingQueue<Message>();
		}
		
		Monitor monitor = new Monitor(queues, 10, 15);
		Thread monitorThread = new Thread(monitor);
		monitorThread.start();
		
		HttpServer server;
		try {
			server = HttpServer.create(new InetSocketAddress(8082), 0);
	        server.createContext("/api/messages", new PostHandler(queues));
	        server.setExecutor(null);
	        server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < threadsNumber; i++) {
			sensors[i] = new Sensor(i);
			
			Thread thread = new Thread(sensors[i]);
			thread.start();

		}
	}

}
