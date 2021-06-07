package org.jibble.pircbot;

/**
 * 
 * @author therealcmiller Twitch chatbot based on PircBot for
 *         twitch.tv/therealcmiller
 *
 */
public class Main extends PircBot {

	public static void main(String[] args) throws Exception {

		// Now start our bot up.
		Cmillerbot bot = new Cmillerbot();

		// Enable debugging output.
		bot.setVerbose(true);

		// Connect to the IRC server.
		bot.connect("irc.twitch.tv", 6667, Config.oAuth);
		// Twitch Stuff

		bot.sendRawLineViaQueue("CAP REQ :twitch.tv/membership");
		bot.sendRawLineViaQueue("CAP REQ :twitch.tv/commands");
		bot.sendRawLine("CAP REQ :twitch.tv/commands");
		// bot.sendRawLineViaQueue("CAP REQ :twitch.tv/tags");
		// Join the channel.
		// bot.joinChannel("#channelname");
		bot.joinChannel("#" + Config.channel);

	}
}