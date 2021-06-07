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
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

class TMIChat {

	public static String url() throws IOException {
		URL url = new URL(
				"https://tmi.twitch.tv/group/user/therealcmiller/chatters?client_id="+Config.client_id+"&?api_version=5");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2 ? httpConn.getInputStream()
				: httpConn.getErrorStream();
		@SuppressWarnings("resource")
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
		return response;
	}

	public static String mods() {

		JsonParser TMIParser = new JsonParser();
		try {
			JsonObject TMIObject = (JsonObject) TMIParser.parse(TMIChat.url());
			JsonObject chatterObject = (JsonObject) TMIObject.get("chatters");
			JsonArray mods = (JsonArray) chatterObject.get("moderators");
			Iterator<JsonElement> iterator = mods.iterator();
			while (iterator.hasNext()) {
				Object modList = iterator.next();
				return modList.toString();
			}
		} catch (JsonSyntaxException | NullPointerException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String viewers() {

		JsonParser TMIParser = new JsonParser();
		try {
			JsonObject TMIObject = (JsonObject) TMIParser.parse(TMIChat.url());
			JsonObject chatterObject = (JsonObject) TMIObject.get("chatters");
			JsonArray viewers = (JsonArray) chatterObject.get("viewers");

			Iterator<JsonElement> iterator = viewers.iterator();
			while (iterator.hasNext()) {
				Object viewerList = iterator.next();
				return viewerList.toString();
			}
		} catch (JsonSyntaxException | NullPointerException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String chatterCount() {

		JsonParser TMIParser = new JsonParser();
		try {
			for (int i = 0; i < 1;) {
				JsonObject TMIObject = (JsonObject) TMIParser.parse(TMIChat.url());
				JsonPrimitive chatterPrimitive = (JsonPrimitive) TMIObject.get("chatter_count");
				return chatterPrimitive.toString();
			}
		} catch (JsonSyntaxException | NullPointerException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String broadcaster() {

		JsonParser TMIParser = new JsonParser();
		try {
			JsonObject TMIObject = (JsonObject) TMIParser.parse(TMIChat.url());
			JsonObject chatterObject = (JsonObject) TMIObject.get("chatters");
			JsonArray broadcaster = (JsonArray) chatterObject.get("broadcaster");

			Iterator<JsonElement> iterator = broadcaster.iterator();
			while (iterator.hasNext()) {
				Object broadcasterObject = iterator.next();
				return broadcasterObject.toString();
			}
		} catch (JsonSyntaxException | NullPointerException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
