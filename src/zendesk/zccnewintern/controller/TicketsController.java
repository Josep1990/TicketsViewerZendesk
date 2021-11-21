package zendesk.zccnewintern.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import zendesk.zccnewintern.model.Tickets;

public class TicketsController {

	private Tickets tickets;
	private static List<Tickets> ticketsData = new ArrayList<Tickets>();

	public List<Tickets> httpConnection() {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://zccnewintern.zendesk.com/api/v2/requests/open.json"))
				.header("Authorization",
						"Basic am9zZS5wc2dAaG90bWFpbC5jb20vdG9rZW46RWp3dVRRZnVlTjJUMGxqODlubHNiMFJEUGFtVDRMSGE5VkxWWTBwQw==")
				.build();

		return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
				.thenApply(TicketsController::parse) // double colon is method reference operator s used to call a
														// method by referring to it with the help of its class
														// directly. They behave exactly as the lambda expressions. The
														// only difference it has from lambda expressions is that this
														// uses direct reference to the method by name instead of
														// providing a delegate to the method.
				.join();

	}

	private static List<Tickets> parse(String responseBody) { 
		

		JSONObject tickets = new JSONObject(responseBody);
		JSONArray ticketsArray = tickets.getJSONArray("requests");// get the array results inside the json obj returned
																	// by the api
		int n = ticketsArray.length();

		for (int i = 0; i < n; ++i) { // loops through the array of tickets and load data in the constructor
			JSONObject info = ticketsArray.getJSONObject(i);
			int id = info.getInt("id");
			String subject = info.getString("subject");
			String description = info.getString("description");

			// save the data on the tickets constructor
			ticketsData.add(new Tickets(id, subject, description));
			// System.out.println("Id: " + id + " Subject: " + subject + " \nBody: " +
			// description.substring(0,10));

		}

		return ticketsData;
	}

}
