import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.jibble.pircbot.Config;
/**
 * 
 * Not sure what this does yet, if anything. 
 *
 */
public class Subscriptions {
public static String url() throws IOException {
	URL url = new URL("https://api.twitch.tv/helix/eventsub/subscriptions");
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
		System.out.println(response);
		return response;
		
	}
}