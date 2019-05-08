package ejercicio1b.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import ejercicio1b.http.Message;

/**
 * Clase que implementa el handler de los requests POST
 * enviados por los sensores
 * 
 * @author diego
 *
 */
public class PostHandler implements HttpHandler {
	private BlockingQueue<Message>[] queues;

	public PostHandler(BlockingQueue<Message>[] queues) {
		this.queues = queues;
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			
			String jsonString = response.toString();
			JSONObject jsonObject = new JSONObject(jsonString);
			int thread = jsonObject.getInt("id");
			int value = jsonObject.getInt("value");
			long timestamp = jsonObject.getLong("timestamp");
			
			//Encola el mensaje que será consumido por el monitor
			Message message = new Message(thread, value, timestamp);
			queues[thread].put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		byte response[] = "OK".getBytes("UTF-8");
		httpExchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
		httpExchange.sendResponseHeaders(200, response.length);

		OutputStream out = httpExchange.getResponseBody();
		out.write(response);
		out.close();

	}

}
