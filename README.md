# cmillerbot
**Twitch chatbot based on pIRCbot**

**You will need to create a "Config.java" file**
   
    package org.jibble.pircbot;

    public interface Config {
    String bearer = "Bearer your_bearer_token";
    String client_id = "your_client_id";
    String channel = "your_channel";
    String oAuth = "oauth:your_oauth_token";
    String botUsername = "your_bots_username";
    }

