# cmillerbot
**Twitch chatbot based on pIRCbot**

**You will need to create a "Config.java" file**


You can get your Bearer token and Client ID by doing [this](https://dev.twitch.tv/docs/authentication#getting-tokens) and setting up your bot [here](https://dev.twitch.tv/console) and your oAuth token can be found [here](https://twitchapps.com/tmi/)
   
    package org.jibble.pircbot;
    /*
     * 
     * Get Bearer Token and Client Id from https://dev.twitch.tv/docs/authentication#getting-tokens
     * Get oAuth Token from https://twitchapps.com/tmi/
     *
     */
    public interface Config {
    String bearer = "Bearer your_bearer_token";
    String client_id = "your_client_id";
    String channel = "your_channel";
    String oAuth = "oauth:your_oauth_token";
    String botUsername = "your_bots_username";
    }

