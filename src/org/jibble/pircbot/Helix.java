package org.jibble.pircbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

class Helix {

	public static String url() throws IOException {
		URL url = new URL("https://api.twitch.tv/helix/search/channels?query=therealcmiller?&first=1");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("Authorization", Config.bearer);
		httpConn.setRequestProperty("Client-Id", Config.client_id);

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		@SuppressWarnings("resource")
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		
		return response;
		
	}
	public String data() {
		// Creating a JSONParser object
		JsonParser jsonParser = new JsonParser();
		try {
			// Parsing the contents of the JSON file
			JsonObject jsonObject = (JsonObject) jsonParser.parse(Helix.url());
			// Retrieving the array
			JsonArray jsonArray = (JsonArray) jsonObject.get("data");
			System.out.println("");
			// Iterating the contents of the array
			Iterator<JsonElement> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
				Object element = iterator.next();
				//System.out.println(element);
				return element.toString();
			}
		} catch (JsonSyntaxException | IOException e) {

			e.printStackTrace();
		}
		return null;
	}
}
