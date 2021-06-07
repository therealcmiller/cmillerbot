package org.jibble.pircbot;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class Subscriptions {
public static String url() throws IOException {
	URL url = new URL("https://api.twitch.tv/helix/eventsub/subscriptions");
	HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	httpConn.setRequestMethod("GET");
	httpConn.setRequestProperty("Authorization", "Bearer 6wztp24zv53813vbi292khft9c85v8");
	httpConn.setRequestProperty("Client-Id", "9wjow729cv1phw7nsd8qppxe8gw45gi");
		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		@SuppressWarnings("resource")
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
		return response;
		
	}
}