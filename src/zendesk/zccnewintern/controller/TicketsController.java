package zendesk.zccnewintern.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import zendesk.zccnewintern.model.Tickets;

public class TicketsController {

	private static ArrayList<Tickets> ticketsData = new ArrayList<Tickets>();
	public List<ArrayList<Tickets>> ticketsList = new ArrayList<>();

	public ArrayList<Tickets> httpConnection() {
		HttpClient client = null;
		HttpRequest request = null;

		String url = "https://zccnewintern.zendesk.com/api/v2/requests/open.json";
		String[] authHeader = { "Authorization",
				"Basic am9zZS5wc2dAaG90bWFpbC5jb20vdG9rZW46RWp3dVRRZnVlTjJUMGxqODlubHNiMFJEUGFtVDRMSGE5VkxWWTBwQw==" };

		client = HttpClient.newHttpClient();
		request = HttpRequest.newBuilder().uri(URI.create(url)).header(authHeader[0], authHeader[1])
				.timeout(Duration.ofSeconds(90)).build();

		return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
				.thenApply(TicketsController::parseJSON).join();
		// double colon is method reference operator s used to call a
		// method by referring to it with the help of its class
		// directly. They behave exactly as the lambda expressions. The
		// only difference it has from lambda expressions is that this
		// uses direct reference to the method by name instead of
		// providing a delegate to the method.

	}

	private static ArrayList<Tickets> parseJSON(String responseBody) {

		try {
			JSONObject tickets = new JSONObject(responseBody);
			JSONArray ticketsArray = tickets.getJSONArray("requests");// get the array requests inside the json obj
																		// returned
																		// by the api
			int n = ticketsArray.length();

			for (int i = 0; i < n; ++i) { // loops through the array of tickets and load data in the constructor
				JSONObject info = ticketsArray.getJSONObject(i);
				int id = info.getInt("id");
				String subject = info.getString("subject");
				String description = info.getString("description");

				// save the data on the tickets constructor
				ticketsData.add(new Tickets(id, subject, description));

			}
		} catch (Exception e) {

			System.out.println("Hey, I am not available at this momment.");
			System.out.println("Please, try again later... ");

		}

		return ticketsData;
	}

	public void ticketPages(ArrayList<Tickets> ticketsData) {

		int ticketsPerPage = 25;
		int counter = 0;
		int numberOfPages = (int) Math.ceil(ticketsData.size() / (float) ticketsPerPage); // round up the number of
																							// pages
		for (int i = 0; i < numberOfPages; i++) {

			ArrayList<Tickets> pages = new ArrayList<>();// create a new page every time it contains 25 tickets

			// ticketsPerPage - 1 because the index in pages start at 0 up to 24 = 25
			for (int j = 0; j <= ticketsPerPage - 1; j++) {
				if (counter < ticketsData.size()) {
					pages.add(ticketsData.get(counter));
					counter++;
				}
			}

			ticketsList.add(pages);
		}

	}

}
