package org.jibble.pircbot;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import PircBot.*;

@SuppressWarnings("unused")
public class Cmillerbot extends PircBot {

	public Cmillerbot() {
		this.setName("cmillerbot");
	}

	protected void onJoin(String channel, String sender, String login, String hostname, String message) {
		if (sender == getName()) {
			sendMessage(channel, "boop");
		}
	}

	// variables

	int opt1 = 0;
	int opt2 = 0;
	String poll1 = "null";
	String poll2 = "null";
	Helix Helix = new Helix();
	String link;
	String offline = "null";
	String[] scale = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	String[] answers = { "Yes", "No" };
	Random rand = new Random();
	String answer = answers[rand.nextInt(answers.length)];
	static String followname = null;

	// All ! commands
	// if(message.startsWith("!command") &&
	// Arrays.asList(modArray).contains(sender)){
	// ^for mod only commands^

	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		Gson gson = new Gson();
		if (sender == "cmillerbot") {
			/* do nothing */}

		if (message.equalsIgnoreCase("!mods")) {
			String mods = null;
			try {
				mods = TMIChat.mods();
				String modsNoQuotes = mods.replace('"', ' ');
				if (mods == null) {
					sendMessage(channel, "No moderators currently in chat");
				} else {
					sendMessage(channel, "Current moderators: " + modsNoQuotes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (message.equalsIgnoreCase("!viewers")) {
			String viewers = null;
			try {
				viewers = TMIChat.viewers();
				sendMessage(channel, "Viewers: " + viewers);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (message.equalsIgnoreCase("!chatters")) {
			String chatters = null;
			String broadcaster = null;

			try {
				chatters = TMIChat.chatterCount();
				broadcaster = TMIChat.broadcaster();
				String broadcasterNoQuotes = broadcaster.replace('"', ' ');
				int chatCount = Integer.parseInt(chatters);
				if (chatCount > 2) {
					sendMessage(channel, "There are currently chatters in the chat");
					sendMessage(channel, "Chatter count: " + chatters);
				} else {
					sendMessage(channel, "There are currently no chatters in the chat besides " + broadcasterNoQuotes
							+ " and I.... I'm lonely.");
					sendMessage(channel, "Chatter count: " + chatters);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * Not sure what to do with this yet, if anything. if
		 * (message.equalsIgnoreCase("!substest")) { try { String result =
		 * Subscriptions.url(); System.out.println(result); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 */
		if (message.equalsIgnoreCase("!racist")) {
			Random num = new Random();
			sendMessage(channel, num.nextInt(scale.length) + "/10");
		}

		if (message.equalsIgnoreCase("!test")) {
			sendMessage(channel, "@" + sender + " Hi! The channel is " + channel + " Testing things, so don't worry.");
		}
		if (message.startsWith("!ask")) {
			sendMessage(channel, answer);
		}

		if (message.startsWith("!game")) {

			String game_name = null;
			try {
				game_name = Helix.data();
				Parse gameName = gson.fromJson(game_name, Parse.class);
				sendMessage(channel, "Current Game: " + gameName.game_name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (message.startsWith("!title")) {

			String title = null;
			try {
				title = Helix.data();
				Parse titleParse = gson.fromJson(title, Parse.class);
				sendMessage(channel, "Current Title: " + titleParse.title);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (message.startsWith("!uptime")) {

			String time = null;
			try {
				time = Helix.data();
				Parse timeParse = gson.fromJson(time, Parse.class);
				if (timeParse.started_at.length() == 0) {
					sendMessage(channel, "Stream is not currently live.");
				} else {
					sendMessage(channel, "Stream started at: " + timeParse.started_at);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (message.startsWith("!live")) {

			String is_live = null;
			try {
				is_live = Helix.data();
				Parse live = gson.fromJson(is_live, Parse.class);
				if (live.is_live == "false") {
					sendMessage(channel, "Channel is currently offline.");
				} else
					sendMessage(channel, "Channel is currently online");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (message.startsWith("!caster")) {
			String entireLine = message;
			String game_name = null;
			String title = null;
			String[] splitEntireLine = entireLine.split(" ");
			if (splitEntireLine.length < 2) {
				sendMessage(channel, "Channel required!");
			}
			followname = splitEntireLine[1];
			try {
				game_name = Caster.data();
				Parse game_nameParse = gson.fromJson(game_name, Parse.class);
				title = Caster.data();
				Parse title_Parse = gson.fromJson(title, Parse.class);
				sendMessage(channel, "Follow @" + followname + " at https://www.twitch.tv/" + followname);
				sendMessage(channel, "They were last playing: " + title_Parse.title + " in the category: "
						+ game_nameParse.game_name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (message.startsWith("!modlist")) {
			sendMessage(channel, "/mods");
		}

		if (message.equals("!feedbot")) {
			sendMessage(channel, "The bot has been fed by " + sender);
		}
		if (message.equalsIgnoreCase("!ping")) {
			final DateFormat time = new SimpleDateFormat("hh:mm:ss a z");
			final DateFormat day = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			sendMessage(channel, "@" + sender + ": PONG! The time is now " + time.format(cal.getTime()) + " on "
					+ day.format(cal.getTime()));
		}

		if (message.contains("!multiset")) {
			String entireLineMulti = message;
			String[] splitEntireLineMulti = entireLineMulti.split(" ");
			if (splitEntireLineMulti.length > 1) {
				link = splitEntireLineMulti[1];
			}
			sendMessage(channel, link);
		}
		if (message.equals("!multi")) {
			sendMessage(channel, link);
		}
		///////////////////// Poll system? Take each separate line and count
		///////////////////// results
		String entireLineMulti = message;
		String[] splitEntireLinePoll = entireLineMulti.split(" # ");
		if (message.contains("!resetpoll")) {
			opt1 = 0;
			opt2 = 0;
			poll1 = "null";
			poll2 = "null";
			sendMessage(channel, "Poll reset");
		}
		if (message.contains("!poll ")) {
			if (splitEntireLinePoll.length > 1) {
				poll1 = splitEntireLinePoll[1];
				poll2 = splitEntireLinePoll[2];
			}
			sendMessage(channel, "Vote for '" + poll1 + "' by typing !vote1  or '" + poll2 + "' by typing !vote2");
		}
		if (message.equals("!currentpoll")) {
			sendMessage(channel, "Vote for '" + poll1 + "' by typing !vote1  or '" + poll2 + "' by typing !vote2");
		}

		if (message.equals("!vote1")) {
			opt1 = opt1 + 1;
		}
		if (message.equals("!vote2")) {
			opt2 = opt2 + 1;
		}
		if (message.contains("!result")) {
			sendMessage(channel, "The results are: ");
			sendMessage(channel, poll1 + ": " + opt1);
			sendMessage(channel, poll2 + ": " + opt2);
			if (opt1 < opt2) {
				sendMessage(channel, "The winner is: " + poll2);
			} else {
				sendMessage(channel, "The winner is: " + poll1);
			}
		}
		//////////////////// Raid message
		if (message.contains("!raid")) {
			String entireLineraid = message;
			String[] splitEntireLineraid = entireLineraid.split(" ");
			String targetName = splitEntireLineraid[1];
			String raidMessage = "";
			for (int i = 2; i < splitEntireLineraid.length; i++) {
				raidMessage = raidMessage + splitEntireLineraid[i];
			}
			sendMessage(channel, "We're raiding @" + targetName + " at https://www.twitch.tv/" + targetName);
			sendMessage(channel, "Copy and paste the raid message and go there! Wait for me to post it first!");
			sendMessage(channel, raidMessage);
		}

		/////// Figured out how to do whispers
		if (message.equalsIgnoreCase("!secret")) {
			sendMessage(channel, "/w " + sender + " shhhh! @" + sender + " it's a secret!");

		}
		/////// Words blacklist, (Has to be a better way to do this?)
		if (message.contains("rape") || message.contains("faggot") || message.contains("fag")
				|| message.contains("bieber")) {
			sendMessage(channel, "@" + sender + " Please don't use that word! (purge)");
			sendMessage(channel, "/timeout " + sender + " 1");
		}
		/////// Checks length of message
		if (message.length() >= 450) {
			sendMessage(channel, "@" + sender + " That's a lot of letters! WOWEE!");
		}
	}

	//////// If a user does /me, copy it
	protected void onAction(String sender, String login, String hostname, String target, String action) {
		if (sender.equals("cmillerbot")) {
			/* do nothing */}
		if (action.contains("@cmillerbot")) {
			String msgReplace2 = action.replace("@cmillerbot", "@" + sender);
			sendAction("#therealcmiller", msgReplace2);
		} else if (action.contains("cmillerbot")) {
			String msgReplace = action.replace("cmillerbot", "@" + sender);
			sendAction("#therealcmiller", msgReplace);
		} else
			sendAction("#therealcmiller", action + " also");
	}

	//////// More whispers
	protected void onChannelInfo(Channel channel, int userCount, String topic) {
	}

	/**
	 * Called when a whisper is received to the bot. This is mainly used with Twitch
	 * TV.
	 * <p>
	 * Note that you MUST call
	 * <code>sendRawLine("CAP REQ :twitch.tv/commands");</code> in order to receive
	 * whispers.
	 *
	 * @param sender
	 *            The user sending the Whisper
	 * @param target
	 *            Who the Whisper is for
	 * @param message
	 *            Whisper Message
	 */
	public void sendWhisper(String user, String message) {
	}

	protected void onWhisper(String sender, String target, String message) {
		sendWhisper(sender, message);

	}
}
