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

public class Caster {
	public static String url() throws IOException {

		String channel = Cmillerbot.followname;
		URL url = new URL("https://api.twitch.tv/helix/search/channels?query=" + channel + "?&first=1");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("Authorization", Config.bearer);
		httpConn.setRequestProperty("Client-Id", Config.client_id);

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2 ? httpConn.getInputStream()
				: httpConn.getErrorStream();
		@SuppressWarnings("resource")
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";

		return response;

	}

	public static String data() {
		JsonParser jsonParser = new JsonParser();
		try {
			JsonObject jsonObject = (JsonObject) jsonParser.parse(Caster.url());
			JsonArray jsonArray = (JsonArray) jsonObject.get("data");
			System.out.println("");

			Iterator<JsonElement> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				Object element = iterator.next();

				return element.toString();
			}
		} catch (JsonSyntaxException | IOException e) {

			e.printStackTrace();
		}
		return null;
	}
}
