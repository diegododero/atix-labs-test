package ejercicio1b.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

public class Sensor implements Runnable {
	private int id;
	
	public Sensor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		Random random = new Random();
		while (true) {
			int value = random.nextInt(100);
			try {
				URL url = new URL ("http://localhost:8082/api/messages");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				connection.setRequestProperty("Accept", "application/json");
				connection.setDoOutput(true);
				
				connection.connect();
				String jsonString = String.format("{\"id\": %d, \"value\": %d, \"timestamp\": %d}", id, value, new Date().getTime());
				try(OutputStream os = connection.getOutputStream()) {
				    byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
				    os.write(input, 0, input.length); 
				    os.flush();
				}
				int httpResult = connection.getResponseCode();
				connection.disconnect();
				
				System.out.println(String.format("Value %d measured by %d", value, id));
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
